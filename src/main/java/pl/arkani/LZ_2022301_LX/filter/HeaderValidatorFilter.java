//package pl.arkani.LZ_2022301_LX.filter;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Order(1)
//public class HeaderValidatorFilter extends OncePerRequestFilter {
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain)
//      throws ServletException,
//      IOException {
//        String countryCode = request.getHeader("X-Country-Code");
//        if ("XYZ".equals(countryCode)) {
//            response.sendError(HttpStatus.BAD_REQUEST.value(), "Invalid Locale");
//            return;
//        }
//        filterChain.doFilter(request, response);
//    }
//
//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//        String path = request.getRequestURI();
//        return "/health".equals(path);
//    }
//}