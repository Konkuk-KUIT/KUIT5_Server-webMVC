package controller;

import core.db.MemoryUserRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jwp.model.User;

import java.io.IOException;
import java.util.Collection;

//@WebServlet("/")
public class HomeController implements Controller {

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        return "/home.jsp";
    }

}
