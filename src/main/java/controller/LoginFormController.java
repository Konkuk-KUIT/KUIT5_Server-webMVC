package controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LoginFormController implements Controller{

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        return "/user/login.jsp";
    }
}
