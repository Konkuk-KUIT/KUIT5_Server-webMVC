package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutController implements Controller{
    @Override
    public String execute(HttpServletRequest req) throws IOException {
        //세션 데이터 삭제
        HttpSession session = req.getSession();
        session.removeAttribute("user");

        return "redirect:/";
    }
}
