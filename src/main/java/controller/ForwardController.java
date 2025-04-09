package controller;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ForwardController implements Controller {
    @Override
    public String execute(HttpServletRequest req) throws IOException {
        return req.getRequestURI();
    }
}
