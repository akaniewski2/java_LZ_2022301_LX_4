package pl.arkani.LZ_2022301_LX.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collection;

@Controller
public class TestRoleController {



    @GetMapping("/admin")
    public String admin1(Principal principal, Model model) {
        model.addAttribute("username",principal.getName());
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        model.addAttribute("authorities",authorities);
        model.addAttribute("details",details);

        return "hello";
    }


    @GetMapping("/admin/2")
    public String admin2(Principal principal, Model model) {
        model.addAttribute("username",principal.getName());
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        model.addAttribute("authorities",authorities);
        model.addAttribute("details",details);

        return "hello";
    }



    @GetMapping("/user")
    public String user1(Principal principal, Model model) {
        model.addAttribute("username",principal.getName());
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        model.addAttribute("authorities",authorities);
        model.addAttribute("details",details);

        return "hello";
    }


    @GetMapping("/user/2")
    public String user2(Principal principal, Model model) {
        model.addAttribute("username",principal.getName());
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        model.addAttribute("authorities",authorities);
        model.addAttribute("details",details);

        return "hello";
    }

    @GetMapping("/user-admin")
    public String user_admin(Principal principal, Model model) {
        model.addAttribute("username",principal.getName());
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        model.addAttribute("authorities",authorities);
        model.addAttribute("details",details);

        return "hello";
    }

    @GetMapping("/user-admin/2")
    public String user_admin2(Principal principal, Model model) {
        model.addAttribute("username",principal.getName());
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        model.addAttribute("authorities",authorities);
        model.addAttribute("details",details);

        return "hello";
    }



    @GetMapping("/guest")
    public String guest(Principal principal, Model model) {
        model.addAttribute("username",principal.getName());
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        model.addAttribute("authorities",authorities);
        model.addAttribute("details",details);

        return "hello";
    }


    @GetMapping("/guest/2")
    public String guest2(Principal principal, Model model) {
        model.addAttribute("username",principal.getName());
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        model.addAttribute("authorities",authorities);
        model.addAttribute("details",details);

        return "hello";
    }


}


