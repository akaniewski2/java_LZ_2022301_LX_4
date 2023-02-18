//package pl.arkani.LZ_2022301_LX.filter;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.core.annotation.Order;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//
//import java.io.IOException;
//
//@Order(1)
//public class LogFilter extends OncePerRequestFilter {
//    private final Logger logger = LoggerFactory.getLogger(LogFilter.class);
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
//        String path = request.getRequestURI();
//        String contentType = request.getContentType();
//        logger.info("Request URL path : {}, Request content type: {}", path, contentType);
//        filterChain.doFilter(request, response);
//    }
//}
