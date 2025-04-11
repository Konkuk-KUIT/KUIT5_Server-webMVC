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

//@WebServlet("/user/login")
public class LoginUserController implements Controller {

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String loginId = req.getParameter("userId");
        String loginPassword = req.getParameter("password");

        User loginUser = MemoryUserRepository.getInstance().findUserById(loginId);


        if(loginUser == null){
            return "redirect:/user/login_failed";
        }

        if(!loginUser.getPassword().equals(loginPassword))
        {
            return "redirect:/user/login_failed";
        }

        HttpSession session = req.getSession();
        session.setAttribute("user", loginUser);

        return "redirect:/";

    }
}
