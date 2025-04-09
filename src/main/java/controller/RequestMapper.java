package controller;

import java.util.HashMap;
import java.util.Map;

public class RequestMapper {
    private Map<String, Controller> controllers;

    public RequestMapper() {
        controllers = new HashMap<>();
        controllers.put("/", new HomeController());
        controllers.put("/user/login", new LoginController());
        controllers.put("/user/signup", new CreateUserController());
        controllers.put("/user/userList", new ListUserController());
        controllers.put("/user/logout", new LogoutController());
        controllers.put("/user/update", new UpdateUserController());
        controllers.put("/user/updateForm", new UpdateUserFormController());
    }

    public Controller getController(String path) {
        return controllers.get(path);
    }
}
