import jakarta.servlet.RequestDispatcher;
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

        RequestMapper requestMapper = new RequestMapper(req, resp);
        String path = requestMapper.getPath();

        if (path == null) {
            throw new ServletException("Wrong HTTP Method");
        }

        if (path.contains("redirect:")) {
            path = requestMapper.getPath().substring("redirect:".length());
            resp.sendRedirect(path);
        } else {
            RequestDispatcher rd = req.getRequestDispatcher(path);
            rd.forward(req, resp);
        }
    }

}
