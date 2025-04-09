import controller.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestMapper {
    private final HttpServletRequest req;
    private final HttpServletResponse resp;
    Map<String,Controller> controllers = new HashMap<>();

    public RequestMapper(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;
        initControllers();
    }

    public String proceed() throws IOException {
        String uri = req.getRequestURI();
        String query = req.getQueryString();

        Controller controller = null;
        if(uri.equals("/")){
            return "/home";
        }

        // 가장 긴 prefix를 먼저 찾기 위해 정렬 (optional)
        List<String> keys = new ArrayList<>(controllers.keySet());
        keys.sort((a, b) -> Integer.compare(b.length(), a.length())); // 긴 경로 우선

        for (String key : keys) {
            if (uri.startsWith(key)) {
                controller = controllers.get(key);
                break;
            }
        }
        if (controller == null) {
            controller = new ForwardController(); // 또는 NotFoundController
        }

        return controller.execute(req);
    }

    private void initControllers(){
        //controllers.put("/", new HomeController());
        controllers.put("/user/signup", new CreateUserController());
        controllers.put("/user/userLogin", new LoginController());
        controllers.put("/user/logout", new LogoutController());
        controllers.put("/user/userList", new ListUserController());
        controllers.put("/user/updateForm", new UpdateUserFormController());
        controllers.put("/user/update", new UpdateUserController());
    }
}
