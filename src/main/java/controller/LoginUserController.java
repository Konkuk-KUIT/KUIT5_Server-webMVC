package controller;

import core.db.MemoryUserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jwp.model.User;

import java.io.IOException;

@WebServlet("/user/login")
public class LoginUserController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String loginId = req.getParameter("userId");
        String loginPassword = req.getParameter("password");

        User loginUser = MemoryUserRepository.getInstance().findUserById(loginId);

        System.out.println("loginPassword = " + loginPassword);
        
        if(loginUser == null){
            resp.sendRedirect("/user/login_failed");
            return;
        }

        System.out.println("loginUser.getPassword() = " + loginUser.getPassword());

        if(!loginUser.getPassword().equals(loginPassword))
        {
            resp.sendRedirect("/user/login_failed");
            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute("user", loginUser);
        resp.sendRedirect("/");



    }
}
