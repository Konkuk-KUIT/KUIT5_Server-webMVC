package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

public class ListUserController implements Controller{
    @Override
    public String execute(HttpServletRequest req) throws IOException {
        HttpSession session = req.getSession(false); // false로 해야 세션이 없으면 새로 만들지 않음
        if (session == null || session.getAttribute("user") == null) {
            return "redirect:/user/login";
        }

        User user = (User) session.getAttribute("user");

        Collection<User> users = MemoryUserRepository.getInstance().findAll();
        req.setAttribute("users", users);
        req.setAttribute("user", user);

        return "/user/list";
    }
}
