package controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CreateUserFormController implements Controller{
    @Override
    public String handle(HttpServletRequest req, HttpServletResponse res) {
        return "/WEB-INF/user/form.jsp";
    }
}
