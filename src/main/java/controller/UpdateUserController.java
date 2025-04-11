package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/user/update")
public class UpdateUserController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        User user = MemoryUserRepository.getInstance().findUserById(userId);

        if (user == null) {
            resp.sendRedirect("/user/userList");
            return;
        }

        user.update(new User(userId, password, name, email));

        resp.sendRedirect("/user/userList");
    }
}
