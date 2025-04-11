package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // 요청 URI 추출
        String requestUri = req.getRequestURI();
        System.out.println("Request URI: " + requestUri);
        String path = requestUri.substring(req.getContextPath().length());

        // RequestMapper 객체를 직접 생성하여 URI에 해당하는 컨트롤러 찾기
        Controller controller = RequestMapper.getInstance().getController(path);

        System.out.println("Extracted Path    : " + path);
        System.out.println("Request From : " + requestUri);
        System.out.println("Respond With : " + path);

        // 컨트롤러가 없으면 404 에러
        if (controller == null) {
            res.sendError(HttpServletResponse.SC_NOT_FOUND, "해당 요청을 처리할 수 없습니다.");
            return;
        }

        try {
            // 컨트롤러에서 반환하는 뷰 경로를 얻음
            String viewPath = controller.execute(req, res);
            System.out.println("View Path: " + viewPath);

            // 반환된 경로가 redirect로 시작하는지 확인
            if (viewPath.startsWith("redirect:")) {
                // redirect 처리
                String redirectUrl = viewPath.substring("redirect:".length());
                res.sendRedirect(redirectUrl);
            } else {
                // forward 처리 (JSP 파일로 전달)
                RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
                dispatcher.forward(req, res);
            }
        } catch (Exception e) {
            // 예외 처리
            throw new ServletException("DispatcherServlet 처리 중 오류 발생", e);
        }
    }
}
