package pl.arkani.LZ_2022301_LX.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.arkani.LZ_2022301_LX.Examples.SqlRepoExec;
import pl.arkani.LZ_2022301_LX.model.TechPage;
import pl.arkani.LZ_2022301_LX.model.TechPageTmp;
import pl.arkani.LZ_2022301_LX.model.User;
import pl.arkani.LZ_2022301_LX.repo.TechPageRepo;
import pl.arkani.LZ_2022301_LX.repo.TokenRepo;
import pl.arkani.LZ_2022301_LX.repo.UserRepo;
import pl.arkani.LZ_2022301_LX.service.UserDetailsServiceImpl;
import pl.arkani.LZ_2022301_LX.service.UserService;
import pl.arkani.LZ_2022301_LX.utils.Functions;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;



@Controller
//@RequestMapping(value = "/")
public class HomeController {

    private UserRepo userRepo;
    private UserService userService;
    private TokenRepo tokenRepo;
    private TechPageRepo techPageRepo;
    private TechPage techPage;

    private SqlRepoExec sqlRepoExec;

    private UserDetailsServiceImpl userDetailsService;

    public HomeController(UserRepo userRepo, UserService userService, TokenRepo tokenRepo, TechPageRepo techPageRepo,  SqlRepoExec sqlRepoExec, UserDetailsServiceImpl userDetailsService) {
        this.userRepo = userRepo;
        this.userService = userService;
        this.tokenRepo = tokenRepo;
        this.techPageRepo = techPageRepo;

        this.techPage = techPageRepo.findByName("home"); // get info from sql table about this page
        this.sqlRepoExec = sqlRepoExec;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/home")
    public String showHome(Model model, Principal principal) {
        model.addAttribute("users", userRepo.findAll());
        // #--- Privileges START ----------------------------------------------------------------------------------------------------------------
        User user = userRepo.findByUsername(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found in DB"));
        String userRole = user.getRole();
        List<TechPage> techPageList = techPageRepo.findByMethodAndRole("GET", userRole);
        //        List<TechPage> techPageList2 = techPageList.stream().filter(s -> s.getRoles().equals(userRole)).collect(Collectors.toList());
        List<TechPageTmp> techPageList2 = new ArrayList<>();

        for (TechPage s : techPageList) {

            techPageList2.add(new TechPageTmp(s.getName(), s.getButton(), s.getHeader(), s.getUrl()));
        }

        // Set<TechPage> techPageList2 = new HashSet<TechPage>();
        // techPageList2.addAll(techPageList);

        Optional<TechPage> techpagePrivilege = techPageRepo.findByMethodAndNameAndRole("GET", this.techPage.getName(), userRole);
        if (techpagePrivilege.isEmpty()) {
            return "_public/home";
        }
        System.out.println("# techPage:" + techPage);
        System.out.println("# techPageList2:" + techPageList2);
        techPageList.forEach(System.out::println);

        // System.out.println("#techpageRole: "+ techpagePrivilege);
        model.addAttribute("thisTechPage", techPage);
        model.addAttribute("techPageList", techPageList2);//przekazuje tylko te definicje stron , do których user ma uprawnienia
        // #--- Privileges END ------------------------------------------------------------------------------------------------------------------
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();

        model.addAttribute("host", Functions.getHostName());
        model.addAttribute("username",principal.getName());
        model.addAttribute("authorities",authorities);
        model.addAttribute("details");
        model.addAttribute("userRole",userRole);

        return "_public/home";
    }
}
