package core.web;


import controller.Controller;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jwp.support.mapper.RequestMappingHandler;

import java.io.IOException;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

private RequestMappingHandler mappingHandler;

    @Override
    public void init(ServletConfig config) throws ServletException {
        mappingHandler = new RequestMappingHandler();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestUri = req.getRequestURI();
        Controller controller = mappingHandler.findController(requestUri);

        String view = controller.process(req, resp);

        if (view.startsWith("redirect:")) {
            resp.sendRedirect(view.substring("redirect:".length()));
        } else {
            RequestDispatcher rd = req.getRequestDispatcher(view);
            rd.forward(req, resp);
        }

    }
}
