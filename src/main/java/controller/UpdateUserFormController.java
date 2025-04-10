package controller;

import core.db.MemoryUserRepository;
import core.web.mvc.Controller;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jwp.model.User;

import java.io.IOException;


public class UpdateUserFormController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        if (req.getMethod().equals("GET")) {
            String userId = req.getParameter("userId");
            User user = MemoryUserRepository.getInstance().findUserById(userId);

            req.setAttribute("user", user);

            return "/user/updateForm.jsp";
        }

        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        User updateUser = new User(userId, password, name, email);

        MemoryUserRepository.getInstance().changeUserInfo(updateUser);

        return "redirect:/user/userList";
    }
}
