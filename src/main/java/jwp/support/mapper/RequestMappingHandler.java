package jwp.support.mapper;

import controller.*;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandler {

    private final Map<String, Controller> mappings = new HashMap<String, Controller>() {{
        put("/user/signup", new CreateUserController());
        put("/", new HomeController());
        put("/user/userList", new ListUserController());
        put("/user/login_failed", new LoginFailedController());
        put("/user/login", new LoginUserController());
        put("/user/logout", new LogoutUserController());
        put("/user/update", new UpdateUserController());
        put("/user/updateForm", new UpdateUserFormController());
        put("/user/loginForm", new LoginFormController());
        put("/user/signupForm", new SignupFormController());
    }};

    public Controller findController(String path) {
        return mappings.get(path);
    }

}
