package controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    private RequestMapper requestMapper;

    @Override
    public void init() throws ServletException {
        requestMapper = new RequestMapper();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getRequestURI().substring(req.getContextPath().length());
        Controller controller = requestMapper.getController(path);
        String view = controller.handleRequest(req, resp);

        if (view.startsWith("redirect:")) {
            resp.sendRedirect(view.substring("redirect:".length()));
        } else {
            RequestDispatcher rd = req.getRequestDispatcher(view);
            rd.forward(req, resp);
        }
    }
}
