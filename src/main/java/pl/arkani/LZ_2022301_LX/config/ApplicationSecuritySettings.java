package pl.arkani.LZ_2022301_LX.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

//https://www.baeldung.com/spring-security-disable-profile
@Configuration
//@Profile("test")
public class ApplicationSecuritySettings {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
         //.requestMatchers("/**"); // bez logowania , without login
        .requestMatchers("/images/**", "/js/**", "/webjars/**","/css/**","/api/**", "/uploads/**");
    }
}


