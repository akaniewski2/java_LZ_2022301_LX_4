//package pl.arkani.LZ_2022301_LX.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import pl.arkani.LZ_2022301_LX.repo.UserRepo;
//import pl.arkani.LZ_2022301_LX.service.UserDetailsServiceImpl;
//////todo: https://www.javainterviewpoint.com/spring-security-jdbcuserdetailsmanager-example/
//// jakies wyrazenia regularne w sciezkach
////https://4programmers.net/Forum/Java/303902-routing_single_page_application_w_spring_boot?p=1446995
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//public class WebSecurityConfig {
//
//    private   UserRepo repo;
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return username ->  repo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User not found in DB"));
//
//    }
//
//    @Bean
//    public AuthenticationProvider authProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService());
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//
//
//    }
//
//    @Bean
//    public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
//
//        return config.getAuthenticationManager();
//    }
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    //Spring Security Architecture Explained
//    //https://www.youtube.com/watch?v=h-9vhFeM3MY
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        // zmiana aby miec dostep do bazy h2
////        http.csrf().disable();
////        http.headers().disable();
//
//
//        http.authorizeHttpRequests()
//                .requestMatchers("/welcome").permitAll()
//                .requestMatchers("/", "/index", "/welcome").permitAll()
//                .requestMatchers("/user_admin/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
//                .requestMatchers("/admin/**").hasAnyRole("ADMIN")
//                .requestMatchers("/user/**").hasAnyRole("USER")
//                .anyRequest().authenticated()
//                .and()
//
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authenticationProvider(authProvider())
//
//                .formLogin().defaultSuccessUrl("/welcome").permitAll()
//                //
//                .and()
//                .logout().logoutUrl("/logout")
//                .deleteCookies("JSESSIONID");
//
//        ;
//        return http.build();
//
//    }
//}
