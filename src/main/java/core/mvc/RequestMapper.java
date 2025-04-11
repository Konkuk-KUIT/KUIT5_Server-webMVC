package core.mvc;

import controller.*;

import java.util.HashMap;
import java.util.Map;

public class RequestMapper {
    private final Map<String, Controller> mappings = new HashMap<>();

    public RequestMapper() {
        mappings.put("/", new HomeController());
        mappings.put("/user/signup", new CreateUserController());
        mappings.put("/user/signupForm", new SignupFormController());
        mappings.put("/user/login", new LoginController());
        mappings.put("/user/loginForm", new LoginFormController());
        mappings.put("/user/loginFailed", new LoginFailedController());
        mappings.put("/user/logout", new LogoutController());
        mappings.put("/user/userList", new ListUserController());
        mappings.put("/user/update", new UpdateUserController());
        mappings.put("/user/updateForm", new UpdateUserFormController());
    }

    public Controller getController(String path) {
        return mappings.get(path);
    }
}