package controller;

import jakarta.servlet.HttpConstraintElement;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//WebServlet("/user/login_failed")
public class LoginFailedController implements Controller {

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        return "/user/login_failed.jsp";
    }
}
