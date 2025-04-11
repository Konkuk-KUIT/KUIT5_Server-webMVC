package controller;

import core.db.MemoryUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jwp.model.User;

import java.io.IOException;

public class LoginUserController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");

        // 사용자 정보 조회
        User user = MemoryUserRepository.getInstance().findUserById(userId);

        // 로그인 성공 체크
        if (user != null && user.getPassword().equals(password)) {
            // 세션 정보 저장
            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            return "redirect:/";
        } else {
            return "redirect:/user/login_failed.jsp";
        }
    }
}
