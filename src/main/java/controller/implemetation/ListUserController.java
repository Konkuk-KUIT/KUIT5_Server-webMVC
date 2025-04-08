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
import java.util.Collection;

@Slf4j
public class ListUserController implements Controller {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("MemberList called");

        HttpSession session = request.getSession();
        Object value = session.getAttribute("user");
        if (value != null) {
            Collection<User> users = MemoryUserRepository.getInstance().findAll();
            request.setAttribute("users", users);

            RequestDispatcher rd = request.getRequestDispatcher("/user/list.jsp");
            rd.forward(request, response);
            return;
        }
        response.sendRedirect("/");
    }
}
