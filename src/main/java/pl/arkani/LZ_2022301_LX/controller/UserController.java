package pl.arkani.LZ_2022301_LX.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.arkani.LZ_2022301_LX.Examples.SqlRepoExec;
import pl.arkani.LZ_2022301_LX.model.*;
import org.springframework.ui.Model;
import pl.arkani.LZ_2022301_LX.repo.TechPageRepo;
import pl.arkani.LZ_2022301_LX.repo.TokenRepo;
import pl.arkani.LZ_2022301_LX.repo.UserRepo;
import pl.arkani.LZ_2022301_LX.service.UserDetailsServiceImpl;
import pl.arkani.LZ_2022301_LX.service.UserService;
import pl.arkani.LZ_2022301_LX.utils.Functions;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

// -- mogą byc duble w metodach , bo to sklejka z dwóch projektów
//---------------------------------------------------------------------------------

    private UserRepo userRepo;
    private UserService userService;
    private TokenRepo tokenRepo;
    private TechPageRepo techPageRepo;
    private TechPage techPage;

    private SqlRepoExec sqlRepoExec;

    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public UserController(UserRepo userRepo, UserService userService, TokenRepo tokenRepo, TechPageRepo techPageRepo, SqlRepoExec sqlRepoExec, UserDetailsServiceImpl userDetailsService) {
        this.userRepo = userRepo;
        this.userService = userService;
        this.tokenRepo = tokenRepo;
        this.techPageRepo = techPageRepo;
        this.techPage = techPageRepo.findByName("users"); // definiuje raz strone na której dotyczy kontroler
        this.sqlRepoExec = sqlRepoExec;
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
        return "user/user-signup";
    }

    @PostMapping("/signup")
    public String register(@Valid User user,BindingResult bindingResult) {
        ////system.out.println(user);
        //system.out.println(user.getAuthorities());

        if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(System.out::println);
            return "user/user-signup";
        }

        userService.addUser(user);
        return "user/user-signup";
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
    public String showUserList(Model model,Principal principal) {
        model.addAttribute("users", userRepo.findAll());


        // #--- Privileges START ----------------------------------------------------------------------------------------------------------------
        User user = userRepo.findByUsername(principal.getName()).orElseThrow(()->new UsernameNotFoundException("User not found in DB"));
        String userRole =  user.getRole();
        List<TechPage> techPageList = techPageRepo.findByMethodAndRole("GET", userRole);
//        List<TechPage> techPageList2 = techPageList.stream().filter(s -> s.getRoles().equals(userRole)).collect(Collectors.toList());
        List<TechPageTmp> techPageList2 = new ArrayList<>();

        for (TechPage s :techPageList) {

            techPageList2.add(new TechPageTmp(s.getName(), s.getButton(), s.getHeader(),s.getUrl()));
        }

        // Set<TechPage> techPageList2 = new HashSet<TechPage>();
        // techPageList2.addAll(techPageList);

        Optional<TechPage> techpagePrivilege= techPageRepo.findByMethodAndNameAndRole("GET",this.techPage.getName(),userRole);
        if (techpagePrivilege.isEmpty()) {return "/templates/_home";}
        System.out.println("# techPage:" + techPage);
        System.out.println("# techPageList2:" + techPageList2);
        techPageList.forEach(System.out::println);

        // System.out.println("#techpageRole: "+ techpagePrivilege);
        model.addAttribute("thisTechPage",techPage);
        model.addAttribute("techPageList",techPageList2);//przekazuje tylko te definicje stron , do których user ma uprawnienia
        // #--- Privileges END ------------------------------------------------------------------------------------------------------------------

        // #--- Pobranie auto-table START ------------------------------------------------------------------------------------------------------------------

        List<String> headers =new ArrayList<>();
        headers.add("Edit");
        headers.add("Delete");
        headers = sqlRepoExec.getTableHeaders("user_v"); // Arrays.asList("id", "username", "role");

        List<List<String>> rows= sqlRepoExec.getTableData("select concat('/user/edit/',s.id) edit,  '_' de ,s.* from arkani_1.user_v s order by id");



       // model.addAttribute("test", testRepo.findAll());
        model.addAttribute("headers",headers);

        model.addAttribute("headers",headers);
        model.addAttribute("rows", rows);

        // #--- Pobranie auto-table END ------------------------------------------------------------------------------------------------------------------

        return "user/users";
    }

    @GetMapping("/user/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {


        User user = userRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("user", user);
        return "user/user-update";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid User user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "user/user-update";
        }

        userRepo.save(user);
        return "redirect:/user/users";
    }

    @GetMapping("user/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepo.delete(user);
        return "redirect:user/users";
    }
}