package exercise.controller;

import io.javalin.http.HttpStatus;
import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;
import exercise.model.User;
import exercise.util.NamedRoutes;
import java.util.Collections;
import exercise.repository.UserRepository;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.Context;
import java.util.Objects;


public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    // BEGIN
    public static void create(Context ctx) {
        var firstName = ctx.formParam("firstName");
        var lastName = ctx.formParam("lastName");
        var email = ctx.formParam("email");
        var password = Security.encrypt(ctx.formParam("password"));
        var token = Security.generateToken();
        var user = new User(firstName, lastName, email, password, token);
        UserRepository.save(user);
        var userId = user.getId();
        ctx.cookie("token", token);
        ctx.redirect(NamedRoutes.userPath(userId));
    }
    public static void show(Context ctx) {
        var token = ctx.cookie("token");
        var id = ctx.pathParamAsClass("id", Long.class).get();
        try {
            var user = UserRepository.find(id).orElseThrow();
            var actualToken = user.getToken();
            if (Objects.equals(token, actualToken)) {
                ctx.render("users/show.jte", Collections.singletonMap("user", user));
            } else {
                ctx.redirect(NamedRoutes.buildUserPath());
            }
        } catch (Exception e) {
            ctx.redirect(NamedRoutes.buildUserPath());
        }
    }
    // END
}
