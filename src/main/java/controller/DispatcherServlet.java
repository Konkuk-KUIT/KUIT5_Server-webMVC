package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    RequestMapper requestMapper;
    public DispatcherServlet() {
        requestMapper = new RequestMapper();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getRequestURI();
        Controller controller = requestMapper.mapping(path);
        if(controller==null){
            System.out.println("사용자 요청 오류");
            return;
        }

        String view = controller.execute(req,resp);

        if(view.startsWith("redirect:")){
            resp.sendRedirect(view.substring("redirect:".length()));
        }else{
            System.out.println("/WEB-INF"+view+".jsp");
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF"+view+".jsp");
            rd.forward(req,resp);
        }


    }
}
