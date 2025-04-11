package controller;

import core.db.MemoryUserRepository;
import data.KeyEnum;
import data.UrlEnum;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jwp.model.User;

import java.io.IOException;

//@WebServlet("/user/update")
public class UpdateUserController implements Controller {
    //  여기 수정할꺼 보내고 list로 돌아가게 하기
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User updateUser = new User(req.getParameter(KeyEnum.USER_ID.name()),
                req.getParameter(KeyEnum.USER_PASSWORD.name()),
                req.getParameter(KeyEnum.USER_NAME.name()),
                req.getParameter(KeyEnum.USER_EMAIL.name()));
        System.out.println("UpdateUserController");
        MemoryUserRepository.getInstance().changeUserInfo(updateUser);
        return UrlEnum.REDIRECT.getUrl()+ UrlEnum.USER_LIST.getUrl();
    }
}
