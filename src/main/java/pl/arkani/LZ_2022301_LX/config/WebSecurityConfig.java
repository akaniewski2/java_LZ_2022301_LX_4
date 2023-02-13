package pl.arkani.LZ_2022301_LX.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import pl.arkani.LZ_2022301_LX.service.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity

public class WebSecurityConfig {

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();

    }

    private UserDetailsServiceImpl userDetailsService;

    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

   // @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
    //https://www.baeldung.com/spring-security-method-security
  //  @Override
   // @Bean




    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // zmiana aby miec dostep do bazy h2
        http.csrf().disable();
        http.headers().disable();


//        http.authorizeHttpRequests
//                .antMatchers("/wellcome").permitAll()
//                .antMatchers("/hello").authenticated()//   .hasRole("ADMIN")
//                .antMatchers("/admin/**").hasAuthority("ADMIN")//   .hasRole("ADMIN")
//                .antMatchers("/user/**").hasAuthority("USER")//   .hasRole("ADMIN")
//                .antMatchers("/admin-user/**").hasAnyAuthority("ADMIN","USER")
//                .and()
//                .formLogin().loginPage("/login").defaultSuccessUrl("/hello");

        http
                .authorizeHttpRequests((requests) -> requests

                      //  .requestMatchers("/", "/home","/welcome").permitAll()
                        .requestMatchers("/", "/home","/welcome","welcome").permitAll()

                        .anyRequest().authenticated()
//                        .requestMatchers("/hello").authenticated()//   .hasRole("ADMIN")
//                        .requestMatchers("/admin/**").hasAuthority("ADMIN")//   .hasRole("ADMIN")
//                        .requestMatchers("/user/**").hasAuthority("USER")//   .hasRole("ADMIN")
//                        .requestMatchers("/admin-user/**").hasAnyAuthority("ADMIN","USER")
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/hello")
                )
                .logout((logout) -> logout.permitAll());

        return http.build();

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