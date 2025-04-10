package controller;

import core.db.MemoryUserRepository;
import core.web.mvc.Controller;
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


public class ListUserController implements Controller {
    @Override
    public String execute (HttpServletRequest req, HttpServletResponse resp){
        // 세션에 저장된 정보 가져오기
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/user/login.jsp";
        }

        Collection<User> users = MemoryUserRepository.getInstance().findAll();
        req.setAttribute("users",users);

        return "/user/list.jsp";
    }
}
