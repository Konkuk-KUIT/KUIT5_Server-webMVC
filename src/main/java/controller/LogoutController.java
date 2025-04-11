package controller;

import core.db.MemoryUserRepository;
import data.UrlEnum;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jwp.model.User;
import org.apache.jasper.tagplugins.jstl.core.Url;

import java.io.IOException;

//@WebServlet("/user/logout")
public class LogoutController implements Controller{

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("user");
        return UrlEnum.REDIRECT.getUrl()+ UrlEnum.DEFAULT_URL.getUrl();
    }
}
