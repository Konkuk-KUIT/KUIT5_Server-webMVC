import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Set;

@WebFilter("/*")
public class ResourceFilter implements Filter {
    private static final Set<String> resourceExtensions = Set.of("css", "js", "png", "jpg", "jpeg", "gif", "ico", "svg");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();

        // 리소스 확장자 체크
        if (isStaticResource(uri)) {
            // 정적 자원은 필터체인을 계속 타게 해서 톰캣이 직접 처리하도록 함
            chain.doFilter(request, response);
        } else {
            // DispatcherServlet으로 보내기 위해 계속 진행
            chain.doFilter(request, response);
        }
    }

    private boolean isStaticResource(String uri) {
        int idx = uri.lastIndexOf(".");
        if (idx == -1) return false;

        String ext = uri.substring(idx + 1).toLowerCase();
        return resourceExtensions.contains(ext);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void destroy() {

    }
}
