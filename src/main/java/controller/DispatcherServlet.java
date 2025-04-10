package controller;

import core.web.mvc.Controller;
import core.web.mvc.RequestMapper;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.http.HttpRequest;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    private RequestMapper requestMapper;

    @Override
    public void init(ServletConfig config) throws ServletException {
        requestMapper = new RequestMapper();
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String requesetUri = req.getRequestURI();

        Controller controller = requestMapper.get(requesetUri);
        if (controller ==null) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String url = controller.execute(req,res);

        if (url.startsWith("redirect:")) {
            res.sendRedirect(url.substring("redirect:".length()));
            return;
        }
        req.getRequestDispatcher(url).forward(req,res);
    }
}
