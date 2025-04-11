package controller;

import core.db.MemoryUserRepository;
import data.KeyEnum;
import data.UrlEnum;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jwp.model.User;
import org.apache.jasper.tagplugins.jstl.core.Url;

import java.io.IOException;

//@WebServlet()
public class CreateUserController implements Controller {

    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(req.getParameter(KeyEnum.USER_ID.toString()),
                req.getParameter(KeyEnum.USER_PASSWORD.toString()),
                req.getParameter(KeyEnum.USER_NAME.toString()),
                req.getParameter(KeyEnum.USER_EMAIL.toString()));
        MemoryUserRepository.getInstance().addUser(user);
        System.out.println("User created");
        return UrlEnum.REDIRECT.getUrl()+ UrlEnum.DEFAULT_URL.getUrl();
    }
}
