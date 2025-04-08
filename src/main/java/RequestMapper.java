import controller.*;

import java.util.HashMap;
import java.util.Map;

public class RequestMapper {
    private static final Map<String, Controller> controllerMap = new HashMap<>();

    static {
        controllerMap.put("/", new HomeController());
        controllerMap.put("/user/signup", new CreateUserController());
        controllerMap.put("/user/userList", new ListUserController());
        controllerMap.put("/user/login", new LoginController());
        controllerMap.put("/user/logout", new LogoutController());
        controllerMap.put("/user/update", new UpdateUserController());
        controllerMap.put("/user/updateForm", new UpdateUserFormController());
    }

    public Controller getController(String url) {
        return controllerMap.get(url);
    }
    
}
