package controller;

import core.db.MemoryUserRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jwp.model.User;

import java.io.IOException;

@WebServlet("/user/updateForm")
public class UpdateFormController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        User sessionUser = session != null ? (User) session.getAttribute("user") : null;

        if (sessionUser == null) {
            // 로그인 안 했거나 다른 사람 정보 접근 시 -> 접근 불가 처리
            resp.sendRedirect("/user/login.jsp");
            return;
        }

        // 본인일 경우에만 수정 폼 제공
        User user = MemoryUserRepository.getInstance().findUserById(sessionUser.getUserId());
        req.setAttribute("user", user);

        RequestDispatcher rd = req.getRequestDispatcher("/user/updateForm.jsp");
        rd.forward(req, resp);
    }
}

