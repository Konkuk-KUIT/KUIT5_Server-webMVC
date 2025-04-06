package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/updateForm")
public class UpdateUserFormController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        User user = MemoryUserRepository.getInstance().findUserById(userId);

        HttpSession session = req.getSession();
        Object value = session.getAttribute("user");

        if (user == null || value == null) {
            resp.sendRedirect("/");
            return;
        }

        if (!user.equals(value)) {
            resp.sendRedirect("/");
            return;
        }

        req.setAttribute("user", user);
        RequestDispatcher rd = req.getRequestDispatcher("/user/update_form.jsp");
        rd.forward(req, resp);
    }
}