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
import java.util.Collections;

@WebServlet("/user/userList")
public class ListUserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User getUserBySession = (User) session.getAttribute("user");
        if (getUserBySession == null) {
            System.out.println("세션없음");
            resp.sendRedirect("/user/login.jsp");
            return;
        }
        System.out.println("세션있음");


        // 레포지토리의 유저들을 출력
//        Collection<User> users = MemoryUserRepository.getInstance().findAll();
        String userId = req.getParameter("userId");
        User users = MemoryUserRepository.getInstance().findUserById(userId);
        req.setAttribute("users", users);

        RequestDispatcher rd = req.getRequestDispatcher("/user/list.jsp");
        rd.forward(req, resp);
    }

}

