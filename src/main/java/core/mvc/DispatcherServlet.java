package core.mvc;

import controller.Controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/")  // 모든 동적 요청 받음
public class DispatcherServlet extends HttpServlet {

    private RequestMapper mapper;

    @Override
    public void init() throws ServletException {
        mapper = new RequestMapper();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestUri = request.getRequestURI();
        String path = requestUri.replace(request.getContextPath(), "");

        Controller controller = mapper.getController(path);

        if (controller == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "요청을 처리할 컨트롤러를 찾을 수 없습니다.");
            return;
        }

        try {
            String view = controller.execute(request, response);
            if (view.startsWith("redirect:")) {
                response.sendRedirect(view.substring("redirect:".length()));
            } else {
                request.getRequestDispatcher(view).forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "서버 에러");
        }
    }
}
