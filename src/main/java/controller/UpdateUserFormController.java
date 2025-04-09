package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class UpdateUserFormController implements Controller{
    @Override
    public String execute(HttpServletRequest req) throws IOException {
        String id = req.getParameter("userId");
        User user = MemoryUserRepository.getInstance().findUserById(id);
        req.setAttribute("user", user);

        return "/user/updateForm";
    }
}