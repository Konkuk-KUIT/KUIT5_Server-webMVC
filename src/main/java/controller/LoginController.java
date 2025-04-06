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

@WebServlet("/user/login")
public class LoginController extends HttpServlet {

    private MemoryUserRepository userRepository = MemoryUserRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LoginController called");
        String viewPath = "/user/login.jsp";
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(viewPath);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");

        User userById = userRepository.findUserById(userId);

        System.out.println(userById);

        // 세션 생성
        // 세션에 로그인된 회원 정보 보관
        if (userById != null && userById.isSameUser(userId) && userById.matchPassword(password)) {
            HttpSession session = req.getSession();
            session.setAttribute("user", userById);

            resp.setHeader("Set-Cookie", "logined-true");
            resp.sendRedirect("/");
            return;
        }
        resp.sendRedirect("/user/login_failed.jsp");
    }
}
