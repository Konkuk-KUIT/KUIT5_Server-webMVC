import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;


@WebServlet("/")
public class DispatcherServlet extends HttpServlet { // Todo 처리 함수 구현
    private RequestMapper requestMapper;

    @Override
    public void init() {
        this.requestMapper = new RequestMapper();
    }
}
