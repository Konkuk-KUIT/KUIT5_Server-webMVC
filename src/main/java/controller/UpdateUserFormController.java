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
import java.util.Collection;

@WebServlet("/user/update")
public class UpdateUserFormController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        User user = MemoryUserRepository.getInstance().findUserById(userId);
        req.setAttribute("users", user);
        RequestDispatcher rd = req.getRequestDispatcher("/user/updateForm.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        User previousUser = MemoryUserRepository.getInstance().findUserById(userId);
        User updatedUser = new User(req.getParameter("userId"),
                req.getParameter("password"),
                req.getParameter("name"),
                req.getParameter("email"));

        previousUser.update(updatedUser);
        System.out.println("회원정보 수정 완료");
        resp.sendRedirect("/user/userList");
    }
}
