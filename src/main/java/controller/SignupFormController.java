package controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SignupFormController implements Controller{
    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        return "/user/form.jsp";
    }
}
