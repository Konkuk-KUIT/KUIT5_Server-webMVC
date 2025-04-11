package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/user/updateForm")
public class UpdateUserFormController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");

        //id 없으면 홈으로 리다이렉트
        if (userId == null || userId.isEmpty()) {
            resp.sendRedirect("/");
            return;
        }

        User user = MemoryUserRepository.getInstance().findUserById(userId);

        //없는 user면 홈으로 리다이렉트
        if (user == null) {
            resp.sendRedirect("/");
            return;
        }

        req.setAttribute("user", user);
        req.getRequestDispatcher("/user/updateForm.jsp").forward(req, resp);
    }
}
