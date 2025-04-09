package controller;

import core.db.MemoryUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jwp.model.User;

import java.io.IOException;

public class CreateUserController implements Controller {

    @Override
    public String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        User user = new User(req.getParameter("userId"),
                req.getParameter("password"),
                req.getParameter("name"),
                req.getParameter("email")
        );
        MemoryUserRepository.getInstance().addUser(user);
        System.out.println("User 회원가입 완료");

        return "redirect:/user/userList";
    }
}
