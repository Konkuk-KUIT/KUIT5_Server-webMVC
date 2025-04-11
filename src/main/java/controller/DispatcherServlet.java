package controller;

import data.UrlEnum;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.jasper.tagplugins.jstl.core.Url;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    private static final Map<String, Controller> controllers = new HashMap<>();

    static {
        controllers.put(UrlEnum.USER_SIGNUP.getUrl(), new CreateUserController());
        controllers.put(UrlEnum.DEFAULT_URL.getUrl(), new HomeController());
        controllers.put(UrlEnum.USER_LIST.getUrl(), new ListUserController());
        controllers.put(UrlEnum.USER_LOGIN.getUrl(), new LoginController());
        controllers.put(UrlEnum.USER_LOGOUT.getUrl(), new LogoutController());
        controllers.put(UrlEnum.USER_UPDATE_FORM.getUrl(), new UpdateFormController());
        controllers.put(UrlEnum.USER_UPDATE.getUrl(), new UpdateUserController());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url =req.getRequestURI();
        System.out.println(url);
        Controller controller = controllers.get(url);
        String action ="";
        if(controller != null) {
            action=controller.execute(req, resp);
        }
        if(action.startsWith(UrlEnum.REDIRECT.getUrl())){
            resp.sendRedirect(action.substring(UrlEnum.REDIRECT.getUrl().length()));
            return;
        }
        RequestDispatcher rd = req.getRequestDispatcher(action+UrlEnum.JSP.getUrl());
        rd.forward(req, resp);
    }
}
