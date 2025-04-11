package controller;

import core.db.MemoryUserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jwp.model.User;

import java.io.IOException;

@WebServlet("/user/login")
public class LoginController  extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = MemoryUserRepository.getInstance().findUserById(req.getParameter("userId"));
        if(user == null || !user.getPassword().equals(req.getParameter("password"))){
            resp.sendRedirect("/user/login_failed.jsp");
            return;
        }

        // 세션 정보 저장
        HttpSession session = req.getSession();
        session.setAttribute("user", user);

        resp.sendRedirect("/");
    }
}
