package core.web;

import controller.Controller;
import core.web.RequestMapper;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    private RequestMapper requestMapper;

    @Override
    public void init(ServletConfig config) throws ServletException {
        requestMapper = new RequestMapper();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Controller controller = requestMapper.findController(req);
        String view = controller.handle(req, res);

        if (view.startsWith("redirect:")) { // redirect
            res.sendRedirect(view.substring("redirect:".length()));
        } else { // forward
            RequestDispatcher rd = req.getRequestDispatcher(view);
            rd.forward(req, res);
        }
    }
}
