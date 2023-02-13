package pl.arkani.LZ_2022301_LX.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.arkani.LZ_2022301_LX.model.Token;
import pl.arkani.LZ_2022301_LX.model.User;
import org.springframework.ui.Model;
import pl.arkani.LZ_2022301_LX.repo.TokenRepo;
import pl.arkani.LZ_2022301_LX.repo.UserRepo;
import pl.arkani.LZ_2022301_LX.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collection;

@Controller
public class UserController {

// -- mogą byc duble w metodach , bo to sklejka z dwóch projektów
//---------------------------------------------------------------------------------

    private UserRepo userRepo;
    private UserService userService;
    private TokenRepo tokenRepo;

    @Autowired
    public UserController(UserRepo userRepo, UserService userService, TokenRepo tokenRepo) {
        this.userRepo = userRepo;
        this.userService = userService;
        this.tokenRepo = tokenRepo;
    }

    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "user-signup";
    }

    @PostMapping("/signup")
    public String register(User user) {
        System.out.println(user);
        user.setRole("USER");
        System.out.println(user.getAuthorities());
        userService.addUser(user);
        return "user-signup";
    }


    @GetMapping("/token")
    public String signUp(@RequestParam String value) {
        Token byValue =tokenRepo.findByValue(value);

        User user = byValue.getAppUser();
        user.setEnabled(true);
        userRepo.save(user); //aktualizacja flagi
        // model.addAttribiute("user",new AppUser());
        return "redirect:/login";
    }

    @GetMapping("/welcome")
    public String welcome(User user) {
        return "welcome";
    }

    @GetMapping("/hello")
    public String hello(Principal principal, Model model) {
        model.addAttribute("username",principal.getName());
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        model.addAttribute("authorities",authorities);
        model.addAttribute("details",details);

        return "hello";
    }
//---------------------------------------------------------------------------------
    
    @PostMapping("/user-add")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user-add";
        }
        
        userRepo.save(user);
        return "redirect:/users";
    }

    // additional CRUD methods
    @GetMapping("/users")
    public String showUserList(Model model) {
        model.addAttribute("users", userRepo.findAll());
        return "users";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("user", user);
        return "user-update";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid User user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "user-update";
        }

        userRepo.save(user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepo.delete(user);
        return "redirect:/users";
    }
}