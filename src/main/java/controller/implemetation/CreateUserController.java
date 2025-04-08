package controller.implemetation;

import controller.Controller;
import core.db.MemoryUserRepository;
import jwp.model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import lombok.extern.slf4j.Slf4j;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Slf4j
public class CreateUserController implements Controller {

    MemoryUserRepository memoryUserRepository = MemoryUserRepository.getInstance();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("SignupController called");
        if (request.getMethod().equalsIgnoreCase("GET")) {
            String viewPath = "/user/form.jsp";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewPath);
            requestDispatcher.forward(request, response);
        } else if (request.getMethod().equalsIgnoreCase("POST")) {
            User user = new User(request.getParameter("userId"),
                    request.getParameter("password"),
                    request.getParameter("name"),
                    request.getParameter("email"));
            MemoryUserRepository.getInstance().addUser(user);
            log.info("User 회원가입 완료");

            // 잘 전달되었는지 확인
            response.sendRedirect("/user/userList");
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
