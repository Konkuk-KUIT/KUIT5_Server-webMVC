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
import jakarta.servlet.http.HttpSession;
import jwp.model.User;

import java.io.IOException;
import java.util.Collection;

//@WebServlet("/user/updateForm")
public class UpdateFormController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter(KeyEnum.USER_ID.toString());
        // 세션에 저장된 정보 가져오기
        HttpSession session = req.getSession();
        Object value = session.getAttribute(KeyEnum.USER_SESSION_KEY.toString());
        User user = (User) value;

        if(value !=null &&user.getUserId().equals(userId)) {
            User newuser = MemoryUserRepository.getInstance().findUserById(userId);
            req.setAttribute(KeyEnum.USER_SESSION_KEY.toString(), newuser);
            return UrlEnum.USER_UPDATE_FORM.getUrl();
        }
        return UrlEnum.REDIRECT.getUrl()+ UrlEnum.USER_LIST.getUrl();
    }


}