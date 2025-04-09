package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginController implements Controller {
    @Override
    public String execute(HttpServletRequest req) throws IOException {
        String id = req.getParameter("userId");
        String password = req.getParameter("password");

        User user = MemoryUserRepository.getInstance().findUserById(id);
        if(user != null && user.getPassword().equals(password)) {
            System.out.println("User 로그인 완료");

            // 세션 정보 저장
            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            return "redirect:/";
        }
        System.out.println("User 로그인 실패");
        return "redirect:/user/login_failed";
    }
}
