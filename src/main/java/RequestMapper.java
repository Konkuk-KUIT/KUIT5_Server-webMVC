import controller.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

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
        controllers.put("/user/signup",new CreateUserController());
        controllers.put("/", new HomeController());
        controllers.put("/user/userList",new ListUserController());
        controllers.put("/user/login",new LoginController());
        controllers.put("/user/logout",new LogoutController());
        controllers.put("/user/updateForm",new UpdateUserController());

    }

    public String getPath(){

        return controller.handle(request,response);
    }


}
