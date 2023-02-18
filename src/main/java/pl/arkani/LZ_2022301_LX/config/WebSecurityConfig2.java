//package pl.arkani.LZ_2022301_LX.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//public class WebSecurityConfig2 {
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Autowired
//    DataSource dataSource;
//
//
//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         http
//         .authorizeHttpRequests(auth -> auth
////
////
////
//
//             .authorizeHttpRequests()
//                     .requestMatchers("/h2-console/**").permitAll()
////                .antMatchers("/knights").hasAnyRole("USER","ADMIN")
////                .antMatchers("/newknight").hasAnyRole("ADMIN")
//
//
//                     .requestMatchers("/", "/index", "/welcome").permitAll()
//                     .requestMatchers("/admin/**").hasAnyRole("ADMIN")
//                     .requestMatchers("/user/**").hasAnyRole("USER")
//                     .requestMatchers("/user_admin/**").hasAnyRole("USER", "ADMIN")
//
//                     .anyRequest().authenticated() // domyslna strona logowania - aby skorzystac ze swojej nalezy wylaczyc to
//
//         })
//                .and()
//              // .formLogin().loginPage("/login").defaultSuccessUrl("/knights"); // dla wlasnego formularza
//                .formLogin().defaultSuccessUrl("/hello")
//              ;
//
//        return http.build();
//
//
//
//
//    }
//
//    @Autowired
//    public void securityUsers(AuthenticationManagerBuilder auth) throws Exception {
////        auth.inMemoryAuthentication()
////                .withUser("user1").password("{noop}user1").roles("USER")
////                .and()
////                .withUser("user2").password("{noop}user2").roles("ADMIN");
//
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery("select username,password,enabled from user where user=?")
//                .authoritiesByUsernameQuery("select username,role from user where username=?").passwordEncoder(passwordEncoder())
//        ;
//
//
//
//    }
//}