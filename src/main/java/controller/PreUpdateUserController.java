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

@WebServlet("/user/pre-update")
public class PreUpdateUserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userId = req.getParameter("userId");
        User user= MemoryUserRepository.getInstance().findUserById(userId);

        req.setAttribute("userId", user.getUserId());
        req.setAttribute("name", user.getName());
        req.setAttribute("email", user.getEmail());

        RequestDispatcher rd = req.getRequestDispatcher("/user/updateForm.jsp");
        rd.forward(req, resp);
    }
}
