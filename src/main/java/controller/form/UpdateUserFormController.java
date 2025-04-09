package controller.form;

import controller.Controller;
import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// 정보 수정할 때 userId을 가져와서 jsp에 보내줘야함.
public class UpdateUserFormController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User loginedUser = (User) session.getAttribute("user");

        String userId = request.getParameter("userId");

        if(loginedUser == null || !loginedUser.isSameUser(userId)){
            // 로그인 안 된 상태에서 url에 쿼리와 함께 접근 시
            // 혹은 로그인 상태에서 다른 유저의 수정 페이지로 접근 시
            // 서블릿 예외 처리
            throw new ServletException("권한이 없습니다.");
        }

        User user = MemoryUserRepository.getInstance().findUserById(userId);
        request.setAttribute("user", user);

        return "/user/updateForm.jsp";
    }
}

