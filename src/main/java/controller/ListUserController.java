package controller;


import core.db.MemoryUserRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jwp.model.User;

import java.io.IOException;
import java.util.Collection;

//WebServlet("/user/userList")
public class ListUserController implements Controller {

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        HttpSession session = req.getSession();
        Object value = session.getAttribute("user");

        if(value == null){
            return "/user/loginForm";
        }

        Collection<User> users = MemoryUserRepository.getInstance().findAll();
        req.setAttribute("users", users);
        return "/user/list.jsp";
    }
}
