package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

@WebServlet("/user/userList")
public class ListUserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Object value = session.getAttribute("user");

        if(value == null) {
            // 로그인 안 되어 있으면 로그인 페이지로 리다이렉트
            // sendRedirect는 get요청으로 하는 것임. 따라서 doPost만 구현되어있는 컨트롤러로는 보낼 수 없음.
            // doGet이 되어있으면 가능하지만 doPost만 되어있으면 sendRedirect 못씀. forward를 써야함.
            resp.sendRedirect("/user/login.jsp");
            return;
        }

        // 현재 요청에 유저 리스트 정보 users로 저장
        Collection<User> users = MemoryUserRepository.getInstance().findAll();
        req.setAttribute("users", users);

        RequestDispatcher rd = req.getRequestDispatcher("/user/list.jsp");
        rd.forward(req, resp);
    }
}
