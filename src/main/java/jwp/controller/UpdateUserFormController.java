package jwp.controller;

import core.db.MemoryUserRepository;
import core.mvc.Controller;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateUserFormController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String userId = req.getParameter("userId");
        User user = MemoryUserRepository.getInstance().findUserById(userId);

        HttpSession session = req.getSession();
        Object value = session.getAttribute("user");

        if (user != null && value != null) {
            if (user.equals(value)) {
                return "/user/update_form.jsp";
            }
        }
        return "redirect:/";
    }
}