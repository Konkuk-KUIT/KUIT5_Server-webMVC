package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");

        User loginUser = new User(userId, password);
        User user = MemoryUserRepository.getInstance().findUserById(userId);

        if (user!= null && user.isSameUser(loginUser)) {
            session.setAttribute("user", user);
            resp.sendRedirect("/");
            return;
        }
        resp.sendRedirect("/user/login_failed.jsp");
    }
}