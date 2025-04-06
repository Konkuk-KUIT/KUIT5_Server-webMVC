package core.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

// 인코딩 (UTF-8) 을 강제하여 한글 깨짐 문제 방지
@WebFilter("/*")
public class CharacterEncodingFilter implements Filter {
    private static final String DEFAULT_ENCODING = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        request.setCharacterEncoding(DEFAULT_ENCODING);
        response.setCharacterEncoding(DEFAULT_ENCODING);
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {
    }

}
