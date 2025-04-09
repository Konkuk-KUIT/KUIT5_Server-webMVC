package controller.controllers;

import controller.Controller;
import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateUserController  implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        // 새 사용자 정보로 업데이트
        User updatedUser = new User(userId, password, name, email);
        MemoryUserRepository.getInstance().changeUserInfo(updatedUser);

        // 목록으로 리다이렉트
        return "redirect:/user/userList";
    }
}
