package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class HomeController implements Controller {

    @Override
    public String handle(HttpServletRequest req, HttpServletResponse res) {
        return "/WEB-INF/home.jsp";
    }
}
