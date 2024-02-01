package exercise.controller;

import java.util.Collections;
import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.repository.UsersRepository;
import static exercise.util.Security.encrypt;

import exercise.util.NamedRoutes;
import exercise.util.Security;
import io.javalin.http.Context;
import io.javalin.validation.ValidationError;
import io.javalin.validation.ValidationException;

public class SessionsController {

    // BEGIN
    public static void index(Context ctx) {
        var login = ctx.sessionAttribute("currentUser");
        var page = new MainPage(login);
        ctx.render("index.jte", Collections.singletonMap("page", page));
    }
    public static void build(Context ctx) {
        var login = ctx.sessionAttribute("currentUser");
        var page = new LoginPage((String) login, null);
        ctx.render("build.jte", Collections.singletonMap("page", page));
    }
    public static void create(Context ctx) {

        var error = "Wrong username or password";
        try {
            var login = ctx.formParamAsClass("login", String.class)
                    .check(l -> UsersRepository.existsByName(l), error)
                    .get();
            var user = UsersRepository.findByName(login);
            var password = ctx.formParamAsClass("password", String.class)
                    .check(p -> (Security.encrypt(p)).equals(user.getPassword()), error)
                    .get();
            ctx.sessionAttribute("currentUser", login);
            ctx.redirect(NamedRoutes.rootPath());
        } catch (ValidationException e) {
            var page = new LoginPage(ctx.formParam("login"), error);
            ctx.render("build.jte", Collections.singletonMap("page", page));
        }
    }
    public static void destroy(Context ctx) {
        ctx.removeCookie("JSESSIONID");
        ctx.redirect(NamedRoutes.rootPath());
    }
    // END
}
