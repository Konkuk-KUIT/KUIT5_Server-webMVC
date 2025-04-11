package controller;

import core.db.MemoryUserRepository;
import core.web.Controller;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jwp.model.User;

import javax.naming.ldap.Control;
import java.io.IOException;
import java.util.Collection;

@WebServlet("/user/userList")
public class ListUserController implements Controller {
    private static final String USER_SESSION_KEY = "user";
    @Override
    public String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        HttpSession session = req.getSession();
        User sessionUser = (User) session.getAttribute(USER_SESSION_KEY);
        if (sessionUser == null) {
            return "redirect:/user/login.jsp";
        }
        Collection<User> users = MemoryUserRepository.getInstance().findAll();
        req.setAttribute("userList",users);

        return "/WEB-INF/views/user/list.jsp";
    }
}
