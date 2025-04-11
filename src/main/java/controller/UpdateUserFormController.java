package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UpdateUserFormController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        User loginUser = (User) session.getAttribute("user");

        if (loginUser == null) {
            return "redirect:/user/loginForm";
        }

        String userId = req.getParameter("userId");
        if (!loginUser.getUserId().equals(userId)) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "본인만 수정할 수 있습니다.");
            return null;
        }

        User user = MemoryUserRepository.getInstance().findUserById(userId);
        if (user == null) {
            return "redirect:/";
        }

        req.setAttribute("user", user);
        return "/user/updateForm.jsp";
    }
}
