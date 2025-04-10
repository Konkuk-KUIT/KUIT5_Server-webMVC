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
        Object value = session.getAttribute("user");

        if(value == null){
            RequestDispatcher rd = req.getRequestDispatcher("/user/login.jsp");
            rd.forward(req, resp);
            return;
        }

        if (value != null) {
            User user = (User) value;
        }

        Collection<User> users = MemoryUserRepository.getInstance().findAll();
        req.setAttribute("users", users);
        RequestDispatcher rd = req.getRequestDispatcher("/user/list.jsp");
        rd.forward(req, resp);

    }
}
