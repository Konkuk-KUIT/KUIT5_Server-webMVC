package controller;

import core.db.MemoryUserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jwp.model.User;

import java.io.IOException;

public class LoginController implements Controller{

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse response) {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");

        User findUser = MemoryUserRepository.getInstance().findUserById(userId);

        if (findUser == null || !findUser.matchPassword(password)) {
            return "redirect:/user/login_failed.jsp";
        }

        HttpSession session = req.getSession();
        session.setAttribute("user", findUser);

        return "redirect:/";
    }
}
