package pl.arkani.LZ_2022301_LX.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import pl.arkani.LZ_2022301_LX.repo.UserRepo;
import pl.arkani.LZ_2022301_LX.service.UserDetailsServiceImpl;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
////todo: https://www.javainterviewpoint.com/spring-security-jdbcuserdetailsmanager-example/
// jakies wyrazenia regularne w sciezkach
//https://4programmers.net/Forum/Java/303902-routing_single_page_application_w_spring_boot?p=1446995

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig_new {
//
//    @Autowired
//    public DataSource dataSource;

    @Autowired
    private UserRepo userRepo;
//
//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;
////
//////    @Autowired
//////    CustomAuthenticationProvider customAuthenticationProvider;
////
//    public WebSecurityConfig_new(UserDetailsServiceImpl userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }


    //user details service moze byc zdefiniowane przez bean, albo jak to wczesniej było przez wstrzykniecie  private UserDetailsServiceImpl userDetailsService;
    //reszta musi zostac jak jest
    @Bean
    public UserDetailsService userDetailsService() {
        return username ->  userRepo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User not found in DB"));

    }
//
    @Bean
    public AuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;


    }

//    protected void configure(AuthenticationManagerBuilder auth) throws Exception
//    {
//        auth.userDetailsService(jdbcUserDetailsManager()).passwordEncoder(passwordEncoder());
//    }
//
//    @Bean
//    public JdbcUserDetailsManager jdbcUserDetailsManager()
//    {
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
//        jdbcUserDetailsManager.setDataSource(dataSource);
//
//        return jdbcUserDetailsManager;
//    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//      //  return userDetailsService;
//        return username -> userRepo.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
//    }


//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//
//        authProvider.setUserDetailsService(userDetailsService);
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//
//    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration  config) throws Exception {
        return config.getAuthenticationManager();
    }

    //Spring Security Architecture Explained
    //https://www.youtube.com/watch?v=h-9vhFeM3MY

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // zmiana aby miec dostep do bazy h2
//        http.csrf().disable();
//        http.headers().disable();


        http    .cors().disable()
//        https://www.baeldung.com/spring-channel-security-https
//                .requiresChannel()
//                .requestMatchers("/login*").requiresSecure()
//                .and()
                .authorizeHttpRequests()
//                .requestMatchers("/welcome")

                .requestMatchers("/welcome").permitAll()
                .requestMatchers("/", "/index", "/welcome").permitAll()
                .requestMatchers("/user-admin/**").hasAnyRole("USER", "ADMIN")
                //  .requestMatchers("/user-admin/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                .requestMatchers("/guest/**").hasAnyRole("GUEST")
                .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                .requestMatchers("/user/**").hasAnyRole("USER")
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                //        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authProvider())
                .formLogin().defaultSuccessUrl("/hello",true).permitAll()
                //
                .and()
                .logout().logoutUrl("/logout")
                .deleteCookies("JSESSIONID");

        ;
        return http.build();

    }
}
//    @Bean
//    public DaoAuthenticationConfigurer<AuthenticationManagerBuilder, UserDetailsServiceImpl> userDetailsService(AuthenticationManagerBuilder auth) throws Exception {
//        return auth.userDetailsService(userDetailsService);
//    }

    //https://www.youtube.com/watch?v=KxqlJblhzfI
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
//@Bean
//public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder)
//        throws Exception {
//    return http.getSharedObject(AuthenticationManagerBuilder.class)
//            .userDetailsService(userDetailsService)
//            .passwordEncoder(bCryptPasswordEncoder)
//
//            .and()
//            .build();
//}
   // @Autowired
//    @Bean
//    public JdbcUserDetailsManagerConfigurer<AuthenticationManagerBuilder> securityUsers(AuthenticationManagerBuilder auth,DataSource dataSource) throws Exception {
////        auth.inMemoryAuthentication()
////                .withUser("user1").password("{noop}user1").roles("USER")
////                .and()
////                .withUser("user2").password("{noop}user2").roles("ADMIN");
//
//       return auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery("select username,password,enabled from user where username=?")
//                .authoritiesByUsernameQuery("select username,role from user where username=?").passwordEncoder(passwordEncoder())
//        ;
//    }

//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, DataSource dataSource,AuthenticationManagerBuilder auth)
//            throws Exception {
//
//        JdbcUserDetailsManagerConfigurer<AuthenticationManagerBuilder> authenticationManagerBuilderJdbcUserDetailsManagerConfigurer = auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery("select username,password,enabled from user where username=?")
//                .authoritiesByUsernameQuery("select username,role from user where username=?").passwordEncoder(passwordEncoder());
//
//        return http.getSharedObject(AuthenticationManagerBuilder.class)
//                .userDetailsService(authenticationManagerBuilderJdbcUserDetailsManagerConfigurer.getUserDetailsService())
//                .passwordEncoder(bCryptPasswordEncoder)
//                .and()
//                .build();
//    }

    /*
    *
    * create table users(username varchar(50) not null primary key, password varchar(500) not null,enabled boolean not null );
create table authorities (username varchar(50) not null,authority varchar(50) not null);
alter table  authorities add constraint users_fk foreign key (username) references users (username);
create unique index ix_auth_username on authorities (username,authority);
* */

//    @Bean
//    JdbcUserDetailsManager user(DataSource dataSource, PasswordEncoder passwordEncoder) throws SQLException {
//
//        //String defaultUserSchemaDdlLocation = JdbcUserDetailsManager.DEFAULT_USER_SCHEMA_DDL_LOCATION;
//        ////     trzeba to wykonac reczeni na bazie
//////    create table users(username varchar(50) not null primary key, password varchar(500) not null,enabled boolean not null )
//////    create table authorities (username varchar(50) not null,authority varchar(50) not null,constraint fk_authorities_users foreign key(username) references users(username));
//////    create unique index ix_auth_username on authorities (username,authority);
//
//
//        Connection connection = dataSource.getConnection();
//        Statement stmt = connection.createStatement();
//        ;
//        // stmt.execute(defaultUserSchemaDdlLocation);
//
//        stmt.execute("delete from authorities");
//        stmt.execute("delete from users");
//
//        //  connection.commit();
//
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder.encode("admin"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user = User.builder()
//                .username("user")
//                .password(passwordEncoder.encode("user"))
//                .roles("USER")
//                .build();
//
//        UserDetails guest = User.builder()
//                .username("guest")
//                .password(passwordEncoder.encode("guest"))
//                .roles("GUEST")
//                .build();
//
//
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//        jdbcUserDetailsManager.createUser(admin);
//        jdbcUserDetailsManager.createUser(user);
//        jdbcUserDetailsManager.createUser(guest);
//
//        System.out.println(admin.getPassword());
//        connection.close();
//        return jdbcUserDetailsManager;
//    }

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
//        return configuration.getAuthenticationManager();
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService,PasswordEncoder passwordEncoder){
//
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//
//        return customAuthenticationProvider;
//
//    }
//@Bean
//JdbcUserDetailsManager user(DataSource dataSource,PasswordEncoder passwordEncoder) throws SQLException {
//    JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//
//    return jdbcUserDetailsManager;
//
//}





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