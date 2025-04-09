package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class UpdateUserController implements Controller{
    @Override
    public String execute(HttpServletRequest req) throws IOException {
        String id = req.getParameter("userId");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        User user = MemoryUserRepository.getInstance().findUserById(id);
        User updateUser = new User(id, password, name, email);
        user.update(updateUser);
        System.out.println("회원정보 수정 완료");

        return "redirect:/user/userList";
    }
}
