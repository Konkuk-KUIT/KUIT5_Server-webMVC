package controller;

import core.db.MemoryUserRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jwp.model.User;

import java.io.IOException;

public class UpdateFormController implements Controller {
    private static final String USER_SESSION_KEY = "user";

    @Override
    public String handle(HttpServletRequest req, HttpServletResponse res) {
        HttpSession session = req.getSession();
        User sessionUser = (User) session.getAttribute(USER_SESSION_KEY);

        if (sessionUser == null) {
            return "redirect:/WEB-INF/user/login.jsp";
        }

        String userId = req.getParameter("userId");
        if (!sessionUser.isSameUser(userId)) {
            System.out.println("본인이 아니므로 수정 불가");
            return "redirect:/user/userList";
        }

        User user = MemoryUserRepository.getInstance().findUserById(userId);

        req.setAttribute("user", user);
        return "/WEB-INF/user/updateForm.jsp";

    }
}
