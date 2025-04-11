package core.web;

import controller.*;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

public class RequestMapper {
    private final Map<String, Controller> mappings = new HashMap<>();

    // url 따라 controller 매핑
    public RequestMapper(){
        mappings.put("/", new HomeController());
        mappings.put("/user/userList", new ListUserController());
        mappings.put("/user/signup", new CreateUserController());
        mappings.put("/user/form", new CreateUserFormController());
        mappings.put("/user/login", new LoginFormController());
        mappings.put("/user/loginProcess", new LoginController());
        mappings.put("/user/logout", new LogoutController());
        mappings.put("/user/update", new UpdateUserController());
        mappings.put("/user/updateForm", new UpdateFormController());
    }

    public Controller findController(HttpServletRequest req){
        String uri = req.getRequestURI();
        return mappings.get(uri);
    }

}
