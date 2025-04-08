package controller.implemetation;

import controller.Controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class LogoutController implements Controller {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("LogoutController called");
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        response.sendRedirect("/");
    }
}
