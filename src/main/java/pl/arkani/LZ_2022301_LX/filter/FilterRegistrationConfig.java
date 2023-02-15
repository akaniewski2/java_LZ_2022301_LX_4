//package pl.arkani.LZ_2022301_LX.filter;
//
//
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class FilterRegistrationConfig {
//
//    @Bean
//    public FilterRegistrationBean<LogFilter> logFilter() {
//        FilterRegistrationBean<LogFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new LogFilter());
//        registrationBean.addUrlPatterns("/hello", "/welcome" ,"/arkani2/*");
//        return registrationBean;
//    }
//
//
//    @Bean
//    public FilterRegistrationBean<HeaderValidatorFilter> headerValidatorFilter() {
//        FilterRegistrationBean<HeaderValidatorFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new HeaderValidatorFilter());
//        registrationBean.addUrlPatterns("*");
//        return registrationBean;
//    }
//}
