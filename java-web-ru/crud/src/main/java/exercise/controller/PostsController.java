package exercise.controller;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;

import exercise.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import io.javalin.http.NotFoundResponse;

public class PostsController {

    // BEGIN
    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        try {
            var page = new PostPage(PostRepository.find(id).orElseThrow());
            ctx.render("posts/show.jte", Collections.singletonMap("page", page));
        } catch (NoSuchElementException e) {
            ctx.status(HttpStatus.NOT_FOUND);
        }
    }

    public static void index(Context ctx) {
        var currentPage = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
        var per = 5;
        var posts = PostRepository.getEntities().subList((currentPage - 1) * per, currentPage * per);
        var page = new PostsPage(posts, currentPage);
        ctx.render("posts/index.jte", Collections.singletonMap("page", page));
    }
    // END
}
