import controller.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestMapper {
    private final HttpServletRequest req;
    Map<String,Controller> controllers = new HashMap<>();

    public RequestMapper(HttpServletRequest req) {
        this.req = req;
        initControllers();
    }

    public String proceed() throws IOException, URISyntaxException {
        URI uri = new URI(req.getRequestURI());
        String path = uri.getPath();

        Controller controller = null;
        List<String> keys = new ArrayList<>(controllers.keySet());

        for (String key : keys) {
            if (path.equals(key)) {
                controller = controllers.get(key);
                break;
            }
        }
        if (controller == null) {
            controller = new ForwardController();
        }

        return controller.execute(req);
    }

    private void initControllers(){
        controllers.put("/", new HomeController());
        controllers.put("/user/signup", new CreateUserController());
        controllers.put("/user/userLogin", new LoginController());
        controllers.put("/user/logout", new LogoutController());
        controllers.put("/user/userList", new ListUserController());
        controllers.put("/user/updateForm", new UpdateUserFormController());
        controllers.put("/user/update", new UpdateUserController());
    }
}
