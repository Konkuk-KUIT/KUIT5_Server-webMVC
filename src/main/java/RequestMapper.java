import controller.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

import static controller.URI.*;


public class RequestMapper{

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    private static final Map<String, Controller> controllers = new HashMap<>();

    private final Controller controller;

    public RequestMapper(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.controller = controllers.get(request.getServletPath());
    }

    static {
        controllers.put(SIGNUP.getPath(),new CreateUserController());
        controllers.put(HOME.getPath(), new HomeController());
        controllers.put(USER_LIST.getPath(),new ListUserController());
        controllers.put(LOGIN.getPath(),new LoginController());
        controllers.put(LOGOUT.getPath(),new LogoutController());
        controllers.put(UPDATE_FORM.getPath(),new UpdateUserController());

    }

    public String getPath(){

        return controller.handle(request,response);
    }


}
