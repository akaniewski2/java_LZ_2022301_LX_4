package pl.arkani.LZ_2022301_LX.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan
public class WebConfigurer implements WebMvcConfigurer {
    public static String uploadDirectory= System.getProperty("user.home") + "/images";
    public static String uploadDirectory2= System.getProperty("user.dir") + "/uploads";


//    public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/home").setViewName("home");
//		registry.addViewController("/").setViewName("home");
//		registry.addViewController("/hello").setViewName("hello");
//		registry.addViewController("/login").setViewName("login");
//
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println("#uploadDirectory:"+uploadDirectory);

        System.out.println("#uploadDirectory:"+uploadDirectory2);


        registry
                .addResourceHandler("/images/**","/uploads/**")
                .addResourceLocations("file:" + uploadDirectory+"/","file:" + uploadDirectory2+"/");

        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");;


    }
}