import controller.*;

import java.util.HashMap;
import java.util.Map;

public class RequestMapper {
    private final Map<String, Controller> controllers = new HashMap<>();

    public RequestMapper() {
        initControllers();
    }

    private void initControllers() {
        controllers.put("/", new HomeController());
        controllers.put("/user/login", new LoginController());
        controllers.put("/user/logout", new LogoutController());
        controllers.put("/user/signup", new CreateUserController());
        controllers.put("/user/userList", new ListUserController());
        controllers.put("/user/updateForm", new UpdateUserFormController());
        controllers.put("/user/update", new UpdateUserController());
    }

    public Controller get(String uri) {
        return controllers.get(uri);
    }
}
