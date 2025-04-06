package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

@WebServlet("/user/updateForm")
public class UpdateUserController extends HttpServlet {
    private MemoryUserRepository userRepository = MemoryUserRepository.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("UpdateUserController called");

        String userId = req.getParameter("userId");
        User user = userRepository.findUserById(userId);

        System.out.println("User Attribute : " + user);
        req.setAttribute("users", user);

        String viewPath = "/user/updateForm.jsp";
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(viewPath);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        User user = userRepository.findUserById(userId);
        User updatedUser = new User(req.getParameter("userId"),
                req.getParameter("password"),
                req.getParameter("name"),
                req.getParameter("email"));
        System.out.println("Updated Attribute : " + updatedUser);
        user.update(updatedUser);

        resp.sendRedirect("/user/userList");
    }
}
