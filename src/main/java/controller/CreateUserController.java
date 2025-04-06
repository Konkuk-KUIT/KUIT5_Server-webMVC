package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "signUpController", urlPatterns = "/user/signup")
public class CreateUserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("SignUpController called");
        String viewPath = "/user/form.jsp";
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(viewPath);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(req.getParameter("userId"),
        req.getParameter("password"),
        req.getParameter("name"),
        req.getParameter("email"));
        MemoryUserRepository.getInstance().addUser(user);
        System.out.println("User 회원가입 완료");

        // 잘 전달되었는지 확인
        resp.sendRedirect("/user/userList");
    }
}
