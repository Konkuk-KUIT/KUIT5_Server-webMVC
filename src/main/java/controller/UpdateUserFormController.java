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

@WebServlet("/user/updateForm")
public class UpdateUserFormController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        HttpSession session = req.getSession();
        User sessionUser = null;
        Object value = session.getAttribute("user");

        if(value == null){
            RequestDispatcher rd = req.getRequestDispatcher("/user/login.jsp");
            rd.forward(req, resp);
            return;
        }

        if (value != null) {
            sessionUser = (User) value;
        }

        String editUserId = req.getParameter("userId");
        User editUser = MemoryUserRepository.getInstance().findUserById(editUserId);

        if(!sessionUser.getUserId().equals(editUserId)){
            resp.sendRedirect("/user/userList");
            return;
        }


        req.setAttribute("user", editUser);


        RequestDispatcher rd = req.getRequestDispatcher("/user/updateForm.jsp");
        rd.forward(req, resp);
    }

}
