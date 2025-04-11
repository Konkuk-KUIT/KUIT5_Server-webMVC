package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface Controller  {
    public String execute(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException;
}
