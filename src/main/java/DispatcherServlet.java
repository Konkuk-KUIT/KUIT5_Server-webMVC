import controller.Controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    private RequestMapper requestMapper;
    private static final String REDIRECT = "redirect:";

    @Override
    public void init() throws ServletException {
        requestMapper = new RequestMapper();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        Controller controller = requestMapper.getController(url);

        String view = controller.execute(req, resp);
        sendView(req, resp, view);
    }

    private static void sendView(HttpServletRequest req, HttpServletResponse resp, String view) throws IOException, ServletException {
        if (view.startsWith(REDIRECT)) {
            resp.sendRedirect(view.substring(REDIRECT.length()));
            return;
        }
        req.getRequestDispatcher(view).forward(req, resp);
    }
}