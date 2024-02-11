package io.hexlet.servlet;

import java.io.IOException;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "HelloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    // BEGIN
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var name = req.getParameter("name");
        var message = "Hello, " + Optional.of(name).orElse("Guest") + "!";
        req.setAttribute("message", message);
        req.getRequestDispatcher("/WEB-INF/hello.jsp").forward(req, resp);
    }
    // END
}
