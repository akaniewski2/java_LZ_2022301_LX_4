package pl.arkani.LZ_2022301_LX.config;


//todo: https://www.javainterviewpoint.com/spring-security-jdbcuserdetailsmanager-example/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import pl.arkani.LZ_2022301_LX.service.UserDetailsServiceImpl;

import javax.sql.DataSource;

// jakies wyrazenia regularne w sciezkach
//https://4programmers.net/Forum/Java/303902-routing_single_page_application_w_spring_boot?p=1446995

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();

    }

    // tego orginalnie bie bylo, jest to potrzebne do procedury securityUsers
//    @Autowired
//    DataSource dataSource;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    public void WebSecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return  userDetailsService;
//    }


//    @Bean
//    public DaoAuthenticationConfigurer<AuthenticationManagerBuilder, UserDetailsServiceImpl> userDetailsService(AuthenticationManagerBuilder auth) throws Exception {
//        return auth.userDetailsService(userDetailsService);
//    }
    //https://www.baeldung.com/spring-security-method-security
    //  @Override
    // @Bean



//    @Bean
//    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests()
//                .requestMatchers("/", "/home","/welcome","welcome","/arkani2/music","/arkani2/tv_channels").permitAll()
//
//                .and()
//                .formLogin()
//                                .loginPage("/login")
//                                .permitAll()
//                                .defaultSuccessUrl("/hello")
//                .and()
//                .logout().logoutUrl("/logout")
//                .deleteCookies("JSESSIONID");
//
//     return http.build();
//
//    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // zmiana aby miec dostep do bazy h2
//        http.csrf().disable();
//        http.headers().disable();


        http.authorizeHttpRequests()
                .requestMatchers("/welcome").permitAll()
                //    .requestMatchers("/hello").authenticated()//   .hasRole("ADMIN")
                .requestMatchers("/hello").hasAnyRole("ROLE_ADMIN","ROLE_USER")//   .hasRole("ADMIN")
//                .requestMatchers("/admin/**").hasAuthority("ADMIN")//   .hasRole("ADMIN")
//                .requestMatchers("/user/**").hasAuthority("USER")//   .hasRole("ADMIN")
//                .requestMatchers("/admin-user/**").hasAnyAuthority("ADMIN","USER")
                .and()
                .formLogin().defaultSuccessUrl("/welcome").permitAll()
                //
                .and()
                .logout().logoutUrl("/logout")
                .deleteCookies("JSESSIONID");

        ;
        return http.build();

//        http
//
//                .authorizeHttpRequests((requests) -> requests
//
//
//                      //  .requestMatchers("/", "/home","/welcome").permitAll()
//                        .requestMatchers( "/home","/welcome","/arkani2/music","/arkani2/tv_channels","/login*").permitAll()
//
//                  //     .anyRequest().authenticated()
////                        .requestMatchers("/hello").authenticated()//   .hasRole("ADMIN")
////                        .requestMatchers("/admin/**").hasAuthority("ADMIN")//   .hasRole("ADMIN")
////                        .requestMatchers("/user/**").hasAuthority("USER")//   .hasRole("ADMIN")
////                        .requestMatchers("/admin-user/**").hasAnyAuthority("ADMIN","USER")
//                )
//                .formLogin((form) -> form
//                                .loginPage("/login")
////                                .loginProcessingUrl("/login")
//                                .defaultSuccessUrl("/hello.html", true)
//                        .permitAll()
//                      //  .defaultSuccessUrl("/hello")
//                )
//                .logout((logout) -> logout.permitAll());
//
//
//        return http.build();

    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("password")
//                        .roles("USER")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }
}


// tak jest wyciągany user w projekcie 01_SpringPodstawy

//    @Autowired
//    public void securityUsers(AuthenticationManagerBuilder auth) throws Exception {
////        auth.inMemoryAuthentication()
////                .withUser("user1").password("{noop}user1").roles("USER")
////                .and()
////                .withUser("user2").password("{noop}user2").roles("ADMIN");
//
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery("select user_name,password,enabled from player_information where user_name=?")
//                .authoritiesByUsernameQuery("select user_name,role from role where user_name=?").passwordEncoder(passwordEncoder())
//        ;
//
//
//
//    }