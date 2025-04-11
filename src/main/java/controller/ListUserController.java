package controller;

import core.db.MemoryUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jwp.model.User;
import java.io.IOException;
import java.util.Collection;

public class ListUserController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws IOException {
        // 세션에서 로그인 유저 정보 가져오기
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");

        if (user == null || session.getAttribute("user") == null ) {
            return "redirect:/user/login.jsp";
        }

        Collection<User> users = MemoryUserRepository.getInstance().findAll();
        req.setAttribute("users", users);

        // users 리스트를 표시할 JSP 페이지 경로 반환
        return "/user/list.jsp";
    }
}
