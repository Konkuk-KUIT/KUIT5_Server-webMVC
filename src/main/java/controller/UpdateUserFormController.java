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

//@WebServlet("/user/updateForm")
public class UpdateUserFormController implements Controller {

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        User sessionUser = null;
        Object value = session.getAttribute("user");

        if(value == null){
            return "/user/login.jsp";
        }

        if (value != null) {
            sessionUser = (User) value;
        }

        String editUserId = req.getParameter("userId");
        User editUser = MemoryUserRepository.getInstance().findUserById(editUserId);

        if(!sessionUser.getUserId().equals(editUserId)){
            return "redirect:/user/userList";
        }

        req.setAttribute("user", editUser);

        return "/user/updateForm.jsp";
    }
}
