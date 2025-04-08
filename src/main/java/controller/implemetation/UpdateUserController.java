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
public class UpdateUserController implements Controller {
    private MemoryUserRepository userRepository = MemoryUserRepository.getInstance();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getMethod().equalsIgnoreCase("GET")) {
            System.out.println("UpdateUserController called");

            String userId = request.getParameter("userId");
            User user = userRepository.findUserById(userId);

            HttpSession session = request.getSession();
            Object value = session.getAttribute("user");
            if (value != null && value.equals(user)) {
                System.out.println("User Attribute : " + user);
                request.setAttribute("users", user);

                String viewPath = "/user/updateForm.jsp";
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewPath);
                requestDispatcher.forward(request, response);
                return;
            }
            response.sendRedirect("/user/userList");
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
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
