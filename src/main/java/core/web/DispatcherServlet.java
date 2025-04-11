package core.web;

import controller.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    private final Map<String, Controller> controllerMap = new HashMap<>();
    @Override
    public void init() throws ServletException {
        controllerMap.put("/", new HomeController());
        controllerMap.put("/user/login", new LoginController());
        controllerMap.put("/user/logout", new LogoutController());
        controllerMap.put("/user/userList", new ListUserController());
        controllerMap.put("/user/updateForm", new UpdateUserFormController());
        controllerMap.put("/user/update", new UpdateUserController());
        controllerMap.put("/user/signup", new CreateUserController());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();

        Controller controller = controllerMap.get(requestURI);
        if (controller == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Not Found");
            return;
        }

        String viewName = controller.handleRequest(req, resp);
        if (viewName.startsWith("redirect:")) {
            resp.sendRedirect(viewName.substring(9));
        } else {
            RequestDispatcher rd = req.getRequestDispatcher(viewName);
            rd.forward(req, resp);
        }
    }
}
