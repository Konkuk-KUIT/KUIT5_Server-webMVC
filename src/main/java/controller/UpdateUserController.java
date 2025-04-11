package controller;

import core.db.MemoryUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jwp.model.User;
import java.io.IOException;

public class UpdateUserController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws IOException {
        // 세션에서 로그인 유저 정보 가져오기
        HttpSession session = req.getSession();
        User sessionUser = (User) session.getAttribute("user");

        // 로그인하지 않았거나 세션과 요청된 userId가 다르면 접근 불가
        String userId = req.getParameter("userId");

        // 1. 만약 사용자가 자신의 정보를 수정하려면 수정 폼을 반환
        if ("GET".equals(req.getMethod())) {
            if (sessionUser == null || !sessionUser.getUserId().equals(userId)) {
                res.sendError(HttpServletResponse.SC_FORBIDDEN, "자신의 정보만 수정할 수 있습니다.");
                return null;
            }

            // 해당 유저 정보를 세션에 있는 것과 일치하는지 확인 후 수정 폼 보여주기
            User user = MemoryUserRepository.getInstance().findUserById(userId);
            req.setAttribute("user", user);
            return "/user/update_form.jsp"; // 폼을 보여주는 JSP 경로
        }

        // 2. 만약 사용자가 POST로 정보를 제출하면 수정 작업을 수행
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        // 사용자가 자신이 아닌 정보를 수정하려고 하면 거부
        if (sessionUser == null || !sessionUser.getUserId().equals(userId)) {
            res.sendError(HttpServletResponse.SC_FORBIDDEN, "잘못된 접근입니다: 자신의 정보만 수정할 수 있습니다.");
            return null;
        }

        // 수정된 정보로 사용자 업데이트
        User updatedUser = new User(userId, password, name, email);
        MemoryUserRepository.getInstance().findUserById(userId).update(updatedUser);

        // 수정 후 리스트 페이지로 리다이렉트
        return "redirect:/user/userList";
    }
}
