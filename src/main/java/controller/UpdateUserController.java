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

@WebServlet("/user/updateUser")
public class UpdateUserController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        User sessionUser = session != null ? (User) session.getAttribute("user") : null;

        String userId = req.getParameter("userId");

        if (sessionUser == null || !sessionUser.getUserId().equals(userId)) {
            resp.sendRedirect("/user/login.jsp");
            return;
        }

        // 사용자 정보 업데이트
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User updatedUser = new User(userId, password, name, email);
        MemoryUserRepository.getInstance().changeUserInfo(updatedUser);

        // 세션도 갱신
        session.setAttribute("user", updatedUser);

        resp.sendRedirect("/user/userList");
    }
}
