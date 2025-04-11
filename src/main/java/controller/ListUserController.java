package controller;

import core.db.MemoryUserRepository;
import data.KeyEnum;
import data.UrlEnum;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jwp.model.User;

import java.io.IOException;
import java.util.Collection;

import static data.KeyEnum.USER_SESSION_KEY;

//@WebServlet("/user/userList")
public class ListUserController implements Controller{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<User> users = MemoryUserRepository.getInstance().findAll();
        req.setAttribute(KeyEnum.USER_LIST_KEY.toString(), users);
        HttpSession session = req.getSession();
        Object value = session.getAttribute(USER_SESSION_KEY.toString());
        if (value != null) {
            User user = (User) value;
            return UrlEnum.USER_DETAIL.getUrl();
        }
        return UrlEnum.REDIRECT.getUrl()+UrlEnum.USER_LOGIN.getUrl();
    }
}
