package controller;

import core.db.MemoryUserRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jwp.model.User;

import java.io.IOException;

public class UpdateUserFormController implements Controller {

    @Override
    public String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User sessionUser = (User) session.getAttribute("user");

        if (sessionUser == null) {
            return "redirect:/user/login.jsp";
        }

        String userId = req.getParameter("userId");
        if (!sessionUser.getUserId().equals(userId)) {
            return "redirect:/user/userList";
        }

        User user = MemoryUserRepository.getInstance().findUserById(userId);
        req.setAttribute("user", user);

        RequestDispatcher rd = req.getRequestDispatcher("/user/updateForm.jsp");
        rd.forward(req, resp);

        return null;
    }
}
