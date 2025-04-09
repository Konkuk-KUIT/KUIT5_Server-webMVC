package controller.controllers;

import controller.Controller;
import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loginId = req.getParameter("userId");
        String loginPassword = req.getParameter("password");

        User user = MemoryUserRepository.getInstance().findUserById(loginId);

        if (user != null && user.matchPassword(loginPassword)) {
            // 로그인 성공. 세션 저장
            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            return "redirect:/";  // DispatcherServlet이 sendRedirect 처리함
        }

        return "redirect:/user/loginFailed";  // 로그인 실패
    }
}
