package controller;

import core.db.MemoryUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jwp.model.User;
import java.io.IOException;

public class LogoutUserController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws IOException {
        // 사용자 정보 조회
        String userId = req.getParameter("userId");
        User user = MemoryUserRepository.getInstance().findUserById(userId);

        // 세션 데이터 삭제
        HttpSession session = req.getSession();
        session.removeAttribute("user");

        return "redirect:/";
    }
}
