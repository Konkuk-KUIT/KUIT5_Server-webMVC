package controller;

import core.db.MemoryUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jwp.model.User;

import static controller.URI.*;


public class UpdateUserController implements Controller {

    @Override
    public String doGet(HttpServletRequest req, HttpServletResponse resp) {

        String userId = req.getParameter("userId");
        User user = MemoryUserRepository.getInstance().findUserById(userId);

        req.setAttribute("user", user);

        return UPDATE_FORM.jsp();
    }

    @Override
    public String doPost(HttpServletRequest req, HttpServletResponse resp) {
        User user = new User(req.getParameter("userId"), req.getParameter("password"), req.getParameter("name"), req.getParameter("email"));
        MemoryUserRepository.getInstance().changeUserInfo(user);

        return USER_LIST.getPath();
    }
}
