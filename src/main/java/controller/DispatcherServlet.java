package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        String path = uri.substring(contextPath.length());

        System.out.println(path);

        Controller controller = RequestMapper.getController(path);

        // 없을 시 404 에러 후 return
        if (controller == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // redirect 시 변환한다.
        String pageName = controller.execute(req,resp);
        if (pageName.startsWith("redirect:")) {
            resp.sendRedirect(pageName.substring("redirect:".length()));
            return;
        }

        RequestDispatcher rd = req.getRequestDispatcher(pageName);
        rd.forward(req, resp);
    }
}
