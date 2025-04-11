package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class HomeController implements Controller {


    @Override
    public String doGet(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher rd = req.getRequestDispatcher("/home.jsp");
        return ("/home.jsp");
    }

    @Override
    public String doPost(HttpServletRequest req, HttpServletResponse resp) {
        return null;
    }
}
