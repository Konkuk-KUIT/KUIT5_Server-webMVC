import controller.*;
import core.db.MemoryUserRepository;

import java.util.HashMap;
import java.util.Map;

public class RequestMapper {
    Map<String, Controller> mappingUrlToController = new HashMap<>();
    private static RequestMapper requestMapper;
    private RequestMapper() {
        mappingUrlToController.put("/user/login", new LoginController());
        mappingUrlToController.put("/user/signup", new CreateUserController());
        mappingUrlToController.put("/", new HomeController());
        mappingUrlToController.put("/user/userList", new ListUserController());
        mappingUrlToController.put("/user/logout", new LogOutController());
        mappingUrlToController.put("/user/update", new UpdateUserController());
        mappingUrlToController.put("/user/updateForm", new UpdateUserFormController());

        // Show Form
        mappingUrlToController.put("/user/loginForm", new LoginFormController());
        mappingUrlToController.put("/user/signUpForm", new SignUpFormController());
    }

    public static RequestMapper getInstance() {
        if(requestMapper == null){
            requestMapper = new RequestMapper();
        }
        return requestMapper;
    }

    public Controller getController(String url) {
        return mappingUrlToController.get(url);
    }
}
