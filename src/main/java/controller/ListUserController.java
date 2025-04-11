package controller;

import core.db.MemoryUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jwp.model.User;

import java.util.Collection;

public class ListUserController implements Controller {

    @Override
    public String doGet(HttpServletRequest req, HttpServletResponse resp) {

        HttpSession session = req.getSession();

        if(session.getAttribute("user") == null) {
            return ("redirect:/user/login.jsp");
        }

        if(session.getAttribute("user") != null) {
            Object value = session.getAttribute("user");
            User user = (User) value;

            Collection<User> users = MemoryUserRepository.getInstance().findAll();
            req.setAttribute("users", users);
            req.setAttribute("userId", user.getUserId());

            return ("/user/list.jsp");
        }

        return null;
    }

    @Override
    public String doPost(HttpServletRequest req, HttpServletResponse resp) {
        return null;
    }
}
