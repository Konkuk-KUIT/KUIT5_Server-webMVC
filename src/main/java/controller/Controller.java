package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public interface Controller {
    String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
