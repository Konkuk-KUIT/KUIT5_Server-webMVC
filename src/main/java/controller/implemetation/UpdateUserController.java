package controller.implemetation;

import controller.Controller;
import controller.MyView;
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
public class UpdateUserController implements Controller {
    private MemoryUserRepository userRepository = MemoryUserRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getMethod().equalsIgnoreCase("GET")) {
            System.out.println("UpdateUserController called");

            String userId = request.getParameter("userId");
            log.info("userId value:{}", userId);
            User user = userRepository.findUserById(userId);
            log.info("user value:{} ", user);

            HttpSession session = request.getSession(false);

            User value = (User) session.getAttribute("user");
            log.info("value value:{}", value);

            if (value != null && value.isSameUser(user)) {
                log.info("User Attribute : {}", user);
                request.setAttribute("users", user);
                return new MyView("/user/updateForm.jsp");
            }
            response.sendRedirect("/user/userList");
            return null;
        } else if (request.getMethod().equalsIgnoreCase("POST")) {
            String userId = request.getParameter("userId");
            User user = userRepository.findUserById(userId);
            User updatedUser = new User(request.getParameter("userId"),
                    request.getParameter("password"),
                    request.getParameter("name"),
                    request.getParameter("email"));
            System.out.println("Updated Attribute : " + updatedUser);
            user.update(updatedUser);

            response.sendRedirect("/user/userList");
            return null;
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }
}
