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

@WebServlet("/user/userList")
public class ListUserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        if(session.getAttribute("user") == null) {

            resp.sendRedirect("/user/login.jsp");

        }

        if(session.getAttribute("user") != null) {
            Object value = session.getAttribute("user");
            User user = (User) value;


            Collection<User> users = MemoryUserRepository.getInstance().findAll();
            req.setAttribute("users", users);
            req.setAttribute("userId", user.getUserId());
            RequestDispatcher rd = req.getRequestDispatcher("/user/list.jsp");
            rd.forward(req, resp);
        }
    }
}
