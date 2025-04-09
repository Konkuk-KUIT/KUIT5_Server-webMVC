import controller.Controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        Controller controller = RequestMapper.getInstance().getController(url);

        if (controller == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String viewPath = controller.handleRequest(req, resp);

        if (viewPath.startsWith("redirect:")) {
            resp.sendRedirect(viewPath.substring("redirect:".length()));
        } else {
            req.getRequestDispatcher(viewPath).forward(req, resp);
        }
    }
}
