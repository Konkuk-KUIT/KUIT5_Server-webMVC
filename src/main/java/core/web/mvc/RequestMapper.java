package core.web.mvc;

import controller.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

public class RequestMapper {
    private final Map<String, Controller> controllers = new HashMap<>();

    public RequestMapper() {
        initControllers();
    }

    private void initControllers() {
        controllers.put("/", new HomeController());
        controllers.put("/user/signup", new CreateUserController());
        controllers.put("/user/userList", new ListUserController());
        controllers.put("/user/login", new LoginController());
        controllers.put("/user/logout", new LogoutController());
        controllers.put("/user/updateForm", new UpdateUserFormController());
    }

    public Controller get(String requesetUri) {
        return controllers.get(requesetUri);
    }
}
