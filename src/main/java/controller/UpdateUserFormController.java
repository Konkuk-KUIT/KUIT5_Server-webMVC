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

public class UpdateUserFormController implements Controller{

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse response) {
        User user = MemoryUserRepository.getInstance().findUserById(req.getParameter("userId"));
        req.setAttribute("user", user);

        return "/user/updateForm.jsp";
    }
}