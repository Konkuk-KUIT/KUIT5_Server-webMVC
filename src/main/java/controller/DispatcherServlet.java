package controller;

import controller.implemetation.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    private Map<String, Controller> controllerMap = new HashMap<>();

    public DispatcherServlet() {
        controllerMap.put("/user/signup", new CreateUserController());
        controllerMap.put("/", new HomeController());
        controllerMap.put("/user/userList", new ListUserController());
        controllerMap.put("/user/login", new LoginController());
        controllerMap.put("/user/logout", new LogoutController());
        controllerMap.put("/user/updateForm", new UpdateUserController());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("DispatcherServlet.service");
        String requestURI = req.getRequestURI();
        log.info("requestURI:{}", requestURI);
        Controller controller = controllerMap.get(requestURI);
        if (controller == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        MyView view = controller.process(req, resp);
        if (view == null) {
            log.info("Controller가 null을 반환함");
            return;
        }
        view.render(req, resp);
    }
}
