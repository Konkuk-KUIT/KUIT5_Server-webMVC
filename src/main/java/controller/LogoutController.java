package controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import static controller.URI.*;

public class LogoutController implements Controller {

    @Override
    public String doGet(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        session.removeAttribute("user");


        return HOME.redirect();
    }

    @Override
    public String doPost(HttpServletRequest req, HttpServletResponse resp) {
        return null;
    }
}
