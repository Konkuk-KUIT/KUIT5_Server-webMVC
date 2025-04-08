package controller.implemetation;

import controller.Controller;
import core.db.MemoryUserRepository;
import jwp.model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class LoginController implements Controller {

    private MemoryUserRepository userRepository = MemoryUserRepository.getInstance();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getMethod().equalsIgnoreCase("GET")) {
            log.info("LoginController called");
            String viewPath = "/user/login.jsp";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewPath);
            requestDispatcher.forward(request, response);
        } else if (request.getMethod().equalsIgnoreCase("POST")) {
            String userId = request.getParameter("userId");
            String password = request.getParameter("password");

            User userById = userRepository.findUserById(userId);

            System.out.println(userById);

            // 세션 생성
            // 세션에 로그인된 회원 정보 보관
            if (userById != null && userById.isSameUser(userId) && userById.matchPassword(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("user", userById);

                response.setHeader("Set-Cookie", "logined-true");
                response.sendRedirect("/");
                return;
            }
            response.sendRedirect("/user/login_failed.jsp");
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
