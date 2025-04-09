import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // RequestMapper로 controller 찾기
        RequestMapper mapper = new RequestMapper(req, resp);
        String viewPath = mapper.proceed(); // Controller의 반환값이 view path

        // redirect or forward 처리
        if (viewPath.startsWith("redirect:")) {
            String redirectUrl = viewPath.substring("redirect:".length());
            resp.sendRedirect(redirectUrl);
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views" + viewPath + ".jsp");
            dispatcher.forward(req, resp);
        }
    }
}
