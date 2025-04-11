package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateUserController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        User user = MemoryUserRepository.getInstance().findUserById(userId);

        if (user == null) {
            return "redirect:/user/userList";
        }

        user.update(new User(userId, password, name, email));

        return "redirect:/user/userList";
    }
}