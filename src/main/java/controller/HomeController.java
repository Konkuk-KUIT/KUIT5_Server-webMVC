package controller;

import core.db.MemoryUserRepository;
import data.KeyEnum;
import data.UrlEnum;
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
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<User> users = MemoryUserRepository.getInstance().findAll();
        req.setAttribute(KeyEnum.USER_LIST_KEY.toString(), users);
        return UrlEnum.HOME_URL.getUrl();
    }
}