package controller;

import controller.controllers.*;
import controller.form.CreateUserFormController;
import controller.form.LoginFailedFormController;
import controller.form.LoginFormController;
import controller.form.UpdateUserFormController;

import java.util.HashMap;
import java.util.Map;

public class RequestMapper {
    private static final Map<String, Controller> mapping = new HashMap<>();

    static {
        mapping.put("/", new HomeController());
        mapping.put("/user/signup", new CreateUserController());
        mapping.put("/user/signupForm", new CreateUserFormController());
        mapping.put("/user/userList", new ListUserController());
        mapping.put("/user/login", new LoginController());
        mapping.put("/user/loginForm", new LoginFormController());
        mapping.put("/user/loginFailed", new LoginFailedFormController());
        mapping.put("/user/logout", new LogoutController());
        mapping.put("/user/update", new UpdateUserController());
        mapping.put("/user/updateForm", new UpdateUserFormController());
    }

    public static Controller getController(String path) {
        return mapping.get(path);
    }
}
