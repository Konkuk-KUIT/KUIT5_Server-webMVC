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
        // 1. 세션에서 로그인된 사용자 확인
        HttpSession session = req.getSession(false); // false로 해야 세션이 없으면 새로 만들지 않음
        if (session == null || session.getAttribute("user") == null) {
            // 2. 로그인 안 되어 있으면 리디렉트 후 return
            resp.sendRedirect("/user/login.jsp");
            return;
        }

        // 3. 로그인된 사용자 정보 가져오기
        User user = (User) session.getAttribute("user");

        // 4. 사용자 리스트와 로그인 사용자 정보 request에 담기
        Collection<User> users = MemoryUserRepository.getInstance().findAll();
        req.setAttribute("users", users);
        req.setAttribute("user", user);

        // 5. JSP로 포워딩
        RequestDispatcher rd = req.getRequestDispatcher("/user/list.jsp");
        rd.forward(req, resp);
    }
}
