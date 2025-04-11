package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CreateUserController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        User user = new User(
                req.getParameter("userId"),
                req.getParameter("password"),
                req.getParameter("name"),
                req.getParameter("email")
        );

        MemoryUserRepository.getInstance().addUser(user);
        System.out.println("User 회원가입 완료");

        return "redirect:/user/userList";
    }
}
