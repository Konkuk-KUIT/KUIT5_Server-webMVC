package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = MemoryUserRepository.getInstance().findUserById(req.getParameter("userId"));
        if (user == null) {
            return "/user/login_failed";
        } else if (!user.matchPassword(req.getParameter("password"))) {
            return "/user/login_failed";
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            //forward를 사용하면 url로 새로 접속하는것이 아닌, 애플리케이션의 내부로 이동하는 것이므로 Post 형식이 유지됨.
            //하지만 HomeController는 doPost만 받고있기 때문에 에러
            return "redirect:/";
        }
    }
}
