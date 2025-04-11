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
public class UpdateUserFormController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 세션에 저장된 정보 가져오기
        HttpSession session = req.getSession();
        Object sessionUser = session.getAttribute("user");
        User loginUser = (User) sessionUser;

        String userId = req.getParameter("userId");
        if(!loginUser.getUserId().equals(userId)){
            resp.sendRedirect("/user/userList"); // Todo 얘는 왜 됨?
            return;
        }
        User user = MemoryUserRepository.getInstance().findUserById(userId);
        req.setAttribute("user", user);

        RequestDispatcher rd = req.getRequestDispatcher("/user/updateForm.jsp");
        rd.forward(req, resp);
    }
}

