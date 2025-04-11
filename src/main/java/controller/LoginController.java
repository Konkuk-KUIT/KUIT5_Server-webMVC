package controller;

import core.db.MemoryUserRepository;
import data.KeyEnum;
import data.Method;
import data.UrlEnum;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jwp.model.User;

import java.io.IOException;

//@WebServlet("/user/login")
public class LoginController implements Controller {

    //  여기 수정할꺼 보내고 list로 돌아가게 하기
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        RequestDispatcher rd = req.getRequestDispatcher("/user/login_failed.jsp");
        rd.forward(req, resp);
    }


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/user/login.jsp");
        rd.forward(req, resp);
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getMethod().equalsIgnoreCase(Method.GET.name())) {
            return UrlEnum.USER_LOGIN.getUrl();
        }
        String id = req.getParameter(KeyEnum.USER_ID.toString());
        String password = req.getParameter(KeyEnum.USER_PASSWORD.toString());
        User user = MemoryUserRepository.getInstance().findUserById(id);
        if(user != null) {
            if(user.getPassword().equals(password)) {
                HttpSession session = req.getSession();
                req.getSession().setAttribute(KeyEnum.USER_SESSION_KEY.toString(), user);
                return UrlEnum.REDIRECT.getUrl()+UrlEnum.DEFAULT_URL.getUrl();
            }
        }
        return UrlEnum.LOGIN_FAILED.getUrl();
    }
}