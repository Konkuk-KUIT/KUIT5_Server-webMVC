package controller;

import core.db.MemoryUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jwp.model.User;


public class LoginController implements Controller {

    @Override
    public String doGet(HttpServletRequest req, HttpServletResponse resp) {
        return null;
    }

    @Override
    public String doPost(HttpServletRequest req, HttpServletResponse resp) {
        User user = MemoryUserRepository.getInstance().findUserById(req.getParameter("userId"));


        if (user != null && user.getPassword().equals(req.getParameter("password"))) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            return ("redirect:/");
        } else {
            return ("redirect:/user/login_failed.jsp");
        }


    }
}
