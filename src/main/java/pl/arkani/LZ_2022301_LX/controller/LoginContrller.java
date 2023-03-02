//https://stackoverflow.com/questions/46377708/how-to-create-logout-with-springboot-security
//package pl.arkani.LZ_2022301_LX.controller;
//
//import org.apache.catalina.connector.Response;
//import org.springframework.context.annotation.Scope;
//import org.springframework.http.MediaType;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import pl.arkani.LZ_2022301_LX.model.User;
//
//@Scope("session")
//@Controller
//public class LoginContrller {
//
//
//    @PostMapping("/login")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response login(User credentials) {
//
//        if(credentials == null){
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        }
//
//        try {
//            User userInfo = new User();
//            UserDetails userDetails = userDetailsService.loadUserByUsername(credentials.getUsername
//
//                    // Create authRequest Object with User ind DB, Credentials from Web-client
//                    UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userDetails, credentials.getPassword(), userDetails.getAuthorities());
//
//            // Authenticate the user
//            Authentication authentication = authenticationManager.authenticate(authRequest);
//            SecurityContext securityContext = SecurityContextHolder.getContext();
//            securityContext.setAuthentication(authentication);
//
//            userInfo.setUsername(authentication.getName());
//
//            return Response.status(Response.Status.OK).entity("Login succesfull").build();
//        }
//        catch (Exception e) {
//            SecurityContextHolder.getContext().setAuthentication(null);
//            return Response.status(Response.Status.UNAUTHORIZED).entity("Login failed").build();
//        }
//    }
//}
