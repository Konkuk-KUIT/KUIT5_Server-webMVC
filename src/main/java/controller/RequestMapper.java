package controller;

import java.util.HashMap;

public class RequestMapper {
    HashMap<String,Controller> controllers = new HashMap<>();

    public RequestMapper(){
        controllers.put("/", new HomeController());
        controllers.put("/user/signup",new CreateUserController());
        controllers.put("/user/userList", new ListUserController());
        controllers.put("/user/login", new LoginController());
        controllers.put("/user/logout", new LogoutController());
        controllers.put("/user/updateForm", new UpdateUserController());
    }

    public Controller mapping(String path){
        return controllers.get(path);
    }


}
