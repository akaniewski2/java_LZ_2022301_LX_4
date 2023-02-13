//package pl.arkani.LZ_2022301_LX.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import pl.arkani.LZ_2022301_LX.service.UserDetailsServiceImpl;
//
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Bean
//    public PasswordEncoder getPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//
//    }
//
//    private UserDetailsServiceImpl userDetailsService;
//
//    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
//
//   // @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService);
//    }
//    //https://www.baeldung.com/spring-security-method-security
//  //  @Override
//    public void configure(HttpSecurity http) throws Exception {
//        // zmiana aby miec dostep do bazy h2
//        http.csrf().disable();
//        http.headers().disable();
//
//
//        http.authorizeRequests()
//                .antMatchers("/wellcome").permitAll()
//                .antMatchers("/hello").authenticated()//   .hasRole("ADMIN")
//                .antMatchers("/admin/**").hasAuthority("ADMIN")//   .hasRole("ADMIN")
//                .antMatchers("/user/**").hasAuthority("USER")//   .hasRole("ADMIN")
//                .antMatchers("/admin-user/**").hasAnyAuthority("ADMIN","USER")
//                .and()
//                .formLogin().loginPage("/login").defaultSuccessUrl("/hello");
//
//    }
//}
//
//
//// tak jest wyciągany user w projekcie 01_SpringPodstawy
//
////    @Autowired
////    public void securityUsers(AuthenticationManagerBuilder auth) throws Exception {
//////        auth.inMemoryAuthentication()
//////                .withUser("user1").password("{noop}user1").roles("USER")
//////                .and()
//////                .withUser("user2").password("{noop}user2").roles("ADMIN");
////
////        auth.jdbcAuthentication()
////                .dataSource(dataSource)
////                .usersByUsernameQuery("select user_name,password,enabled from player_information where user_name=?")
////                .authoritiesByUsernameQuery("select user_name,role from role where user_name=?").passwordEncoder(passwordEncoder())
////        ;
////
////
////
////    }