package controller.implemetation;

import controller.Controller;
import controller.MyView;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

//@WebServlet(name = "homeController", urlPatterns = "/")
@Slf4j
public class HomeController implements Controller {
    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("HomeController called");
        return new MyView("/home.jsp");
    }
}
