package jwp.support.mapper;

import controller.*;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandler {

    private final Map<String, Controller> mappings = new HashMap<>();

    public void initMapping() {
        mappings.put("/user/signup", new CreateUserController());
        mappings.put("/", new HomeController());
        mappings.put("/user/userList", new ListUserController());
        mappings.put("/user/login_failed", new LoginFailedController());
        mappings.put("/user/login", new LoginUserController());
        mappings.put("/user/logout", new LogoutUserController());
        mappings.put("/user/update", new UpdateUserController());
        mappings.put("/user/updateForm", new UpdateUserFormController());
        mappings.put("/user/loginForm", new LoginFormController());
        mappings.put("/user/signupForm", new SignupFormController());
    }

    public Controller findController(String path) {
        return mappings.get(path);
    }

}
