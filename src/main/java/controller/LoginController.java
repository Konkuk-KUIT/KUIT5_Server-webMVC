package controller;

import core.db.MemoryUserRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jwp.model.User;

import java.io.IOException;

@WebServlet("/user/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");

        User user = MemoryUserRepository.getInstance().findUserById(userId);

        if (user != null && user.getPassword().equals(password)) {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/user/userList");
        } else {
            RequestDispatcher rd = req.getRequestDispatcher("/user/login_failed.jsp");
            rd.forward(req, resp);
        }
    }
}
