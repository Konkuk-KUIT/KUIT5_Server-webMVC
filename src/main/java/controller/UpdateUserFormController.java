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

@WebServlet()
public class UpdateUserFormController implements Controller{
    @Override
    public String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User sessionUser = (User) session.getAttribute("user");
        String userId = req.getParameter("userId");

        if(sessionUser.getUserId().equals(userId)){
            User user = MemoryUserRepository.getInstance().findUserById(userId);
            req.setAttribute("user", user);
            return "/user/updateForm.jsp";
        }
        return "redirect:/";
    }
}
