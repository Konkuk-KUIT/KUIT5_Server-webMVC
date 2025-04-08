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
import java.util.Collection;

@Slf4j
public class ListUserController implements Controller {
    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("MemberList called");

        HttpSession session = request.getSession();
        Object value = session.getAttribute("user");
        if (value != null) {
            Collection<User> users = MemoryUserRepository.getInstance().findAll();
            request.setAttribute("users", users);

            return new MyView("/user/list.jsp");
        }
        response.sendRedirect("/");
        return null;
    }
}
