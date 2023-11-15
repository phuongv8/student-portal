package perscholas.capstone.interceptor;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;

/**
 * Log the response status
 */

@Component
public class ResponseLoggingFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(ResponseLoggingFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        logger.info("Outgoing response with status: {}", httpServletResponse.getStatus());
    }
}
