package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.http.HttpRequest;

public interface Controller {
    String handle(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException;
}
