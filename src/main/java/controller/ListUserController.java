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
import java.util.Collection;

@WebServlet("/user/userList")
public class ListUserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Object user = session != null ? session.getAttribute("user") : null;

        if (user == null) {
            // 로그인 안 된 사용자 -> 로그인 페이지로 리다이렉트
            resp.sendRedirect("/user/login.jsp");
            return;
        }

        Collection<User> users = MemoryUserRepository.getInstance().findAll();
        req.setAttribute("users", users);

        RequestDispatcher rd = req.getRequestDispatcher("/user/list.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<User> users = MemoryUserRepository.getInstance().findAll();
        req.setAttribute("users", users);

        RequestDispatcher rd = req.getRequestDispatcher("/user/list.jsp");
        rd.forward(req, resp);
    }
}
