package exercise.controller;

import java.util.Collections;
import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.dto.posts.BuildPostPage;
import exercise.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import io.javalin.validation.ValidationException;
import io.javalin.http.NotFoundResponse;

public class PostsController {

    public static void build(Context ctx) {
        var page = new BuildPostPage();
        ctx.render("posts/build.jte", Collections.singletonMap("page", page));
    }

    // BEGIN
    public static void index(Context ctx) {
        var page = new PostsPage(PostRepository.getEntities());
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        ctx.render("posts/index.jte", Collections.singletonMap("page", page));
    }

    public static void create(Context ctx) {
        var body = ctx.formParam("body");
        try {
            var name = ctx.formParamAsClass("name", String.class)
                    .check(n -> n.length() >= 2, "название курса должно быть не короче двух символов")
                    .get();
            var post = new Post(name, body);
            PostRepository.save(post);
            ctx.sessionAttribute("flash", "Пост был успешно создан!");
            ctx.redirect(NamedRoutes.postsPath());
        } catch (ValidationException e) {
            var page = new BuildPostPage(ctx.formParam("name"), body, e.getErrors());
            ctx.status(HttpStatus.UNPROCESSABLE_CONTENT);
            ctx.render("posts/build.jte", Collections.singletonMap("page", page));
        }
    }
    // END

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id)
            .orElseThrow(() -> new NotFoundResponse("Post not found"));

        var page = new PostPage(post);
        ctx.render("posts/show.jte", Collections.singletonMap("page", page));
    }
}
