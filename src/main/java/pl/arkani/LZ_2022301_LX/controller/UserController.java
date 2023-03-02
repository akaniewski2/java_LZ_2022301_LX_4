package pl.arkani.LZ_2022301_LX.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.arkani.LZ_2022301_LX.model.LoginForm;
import pl.arkani.LZ_2022301_LX.model.Token;
import pl.arkani.LZ_2022301_LX.model.User;
import org.springframework.ui.Model;
import pl.arkani.LZ_2022301_LX.repo.TechPageRepo;
import pl.arkani.LZ_2022301_LX.repo.TokenRepo;
import pl.arkani.LZ_2022301_LX.repo.UserRepo;
import pl.arkani.LZ_2022301_LX.service.UserDetailsServiceImpl;
import pl.arkani.LZ_2022301_LX.service.UserService;
import pl.arkani.LZ_2022301_LX.utils.Functions;

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
    private TechPageRepo techPageRepo;

    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public UserController(UserRepo userRepo, UserService userService, TokenRepo tokenRepo, TechPageRepo techPageRepo, UserDetailsServiceImpl userDetailsService) {
        this.userRepo = userRepo;
        this.userService = userService;
        this.tokenRepo = tokenRepo;
        this.techPageRepo = techPageRepo;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping(value = {"/",""} )
    public String homePage(){
        return welcome();
    }

//    @GetMapping(value = "/logout" )
//    public String logout(){
//        return "/logout";
//    }






//    @GetMapping("/")
//    // @ResponseBody //for REST:  @ResponseBody  dodane bo jestesmy w @Controller, a nie @RestController ,w @RestController byłoby to zbedne
//    public String start() {
//        return "login" ;
//
//    }
//
    // !!!!  login @POST https://www.youtube.com/watch?v=Tke5zjCshkc !!!
    // prosty mapping, ale pokazane jak odebrac cały formularz jako model

    @PostMapping("/login_TEST")
    public String login(@ModelAttribute(name="loginForm") LoginForm loginForm , Model model, @Valid User user ,Principal principal,BindingResult result) {

        System.out.println("# my login controller");
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());


        model.addAttribute("username", principal.getName());
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        model.addAttribute("authorities", authorities);
        model.addAttribute("details", details);
        return "/welcome";



    }


    @GetMapping("/login")
    // @ResponseBody //for REST:  @ResponseBody  dodane bo jestesmy w @Controller, a nie @RestController ,w @RestController byłoby to zbedne
    public String start() {
        return "loginForm" ;

    }

    @GetMapping("/login2")
    // @ResponseBody //for REST:  @ResponseBody  dodane bo jestesmy w @Controller, a nie @RestController ,w @RestController byłoby to zbedne
    public String start2() {
        return "loginForm2" ;

    }

////    //todo zwrotki ze zlego logowania
////    @GetMapping("/login")
////    public String login() {
////
////        return "loginForm";
////    }


    @PostMapping("/login_auth2")
    public String login_auth2(@Valid User user, BindingResult result, Model model) {


        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();

        return "/welcome";

    }


    @PostMapping("/loginMy")
    public String login_auth(@Valid User user ,Principal principal, Model model,BindingResult result) {

       UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());


       model.addAttribute("username", principal.getName());
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
       model.addAttribute("authorities", authorities);
       model.addAttribute("details", details);
        return "/welcome";

    }
    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "user-signup";
    }

    @PostMapping("/signup")
    public String register(User user) {
        ////system.out.println(user);

        //system.out.println(user.getAuthorities());
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
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/hello")
    public String hello(Principal principal, Model model) {
       // //system.out.println(Arrays.toString(Functions.getHostAddresses()));
        model.addAttribute("host",Functions.getHostName());
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