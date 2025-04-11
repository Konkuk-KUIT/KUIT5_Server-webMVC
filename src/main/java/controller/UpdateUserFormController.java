package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/user/updateForm")
public class UpdateUserFormController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User loginUser = (User) session.getAttribute("user");

        //로그인 안했으면 리다이렉트
        if (loginUser == null) {
            resp.sendRedirect("/user/login.jsp");
            return;
        }

        String userId = req.getParameter("userId");
        //본인 아니면 오류
        if (!loginUser.getUserId().equals(userId)) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "본인만 수정할 수 있습니다.");
            return;
        }

        User user = MemoryUserRepository.getInstance().findUserById(userId);
        if (user == null) {
            resp.sendRedirect("/");
            return;
        }

        req.setAttribute("user", user);
        req.getRequestDispatcher("/user/updateForm.jsp").forward(req, resp);
    }
}
