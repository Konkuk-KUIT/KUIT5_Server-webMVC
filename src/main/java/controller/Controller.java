package controller;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface Controller {
    String execute(HttpServletRequest req) throws IOException;
}
