package controller;

import core.db.MemoryUserRepository;
import core.web.mvc.Controller;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jwp.model.User;

import java.io.IOException;


public class LoginController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        if (req.getMethod().equals("GET")) {
            return "/user/login.jsp";
        }

        String userId = req.getParameter("userId");
        String password = req.getParameter("password");

        User user = MemoryUserRepository.getInstance().findUserById(userId);

        if (user!=null && user.getPassword().equals(password)) {
            // 세션 정보 저장
            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            return "redirect:/";
        }

        return "/user/login.jsp";
    }
}
