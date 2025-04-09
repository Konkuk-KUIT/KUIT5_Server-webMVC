package controller;

import core.db.MemoryUserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jwp.model.User;

import java.io.IOException;

public class UpdateUserController implements Controller {

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

        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        User user = MemoryUserRepository.getInstance().findUserById(userId);
        user.update(new User(userId, password, name, email));
        MemoryUserRepository.getInstance().changeUserInfo(user);

        return "redirect:/user/userList";
    }
}
