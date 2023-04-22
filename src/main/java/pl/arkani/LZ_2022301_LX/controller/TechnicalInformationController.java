package pl.arkani.LZ_2022301_LX.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.arkani.LZ_2022301_LX.Examples.SqlRepoExec;
import pl.arkani.LZ_2022301_LX.model.TechPage;
import pl.arkani.LZ_2022301_LX.repo.TechPageRepo;
import pl.arkani.LZ_2022301_LX.repo.TokenRepo;
import pl.arkani.LZ_2022301_LX.repo.UserRepo;
import pl.arkani.LZ_2022301_LX.service.TechPageService;
import pl.arkani.LZ_2022301_LX.service.UserDetailsServiceImpl;
import pl.arkani.LZ_2022301_LX.service.UserService;
import pl.arkani.LZ_2022301_LX.utils.Functions;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collection;


@Controller
//@RequestMapping(value = "/")
public class TechnicalInformationController {

    private UserRepo userRepo;
    private UserService userService;
    private TokenRepo tokenRepo;
    private TechPageRepo techPageRepo;
    private TechPageService techPageService;
    private TechPage techPage;

    private SqlRepoExec sqlRepoExec;

    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public TechnicalInformationController(UserRepo userRepo, UserService userService, TokenRepo tokenRepo, TechPageRepo techPageRepo, TechPageService techPageService, SqlRepoExec sqlRepoExec, UserDetailsServiceImpl userDetailsService) {
        this.userRepo = userRepo;
        this.userService = userService;
        this.tokenRepo = tokenRepo;
        this.techPageRepo = techPageRepo;

        this.techPage = techPageRepo.findByName("technicalInformation"); // get info from sql table about thie page
        this.techPageService = techPageService;
        this.sqlRepoExec = sqlRepoExec;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/technicalinformation")
    public String showHome(Model model, Principal principal) {

        System.out.println("#getHostAddresses:"+Arrays.toString(Functions.getHostAddresses()));
        System.out.println("#getHostAddresses2:"+Arrays.toString(Functions.getHostAddresses2()));

        model.addAttribute("users", userRepo.findAll());
        // #--- Privileges START ----------------------------------------------------------------------------------------------------------------
        String userRole = techPageService.checkPrivilage("get",this.techPage.getName(),model, principal,
                                                          techPageRepo, techPage, userRepo);
        if (userRole == null) return "_public/error";
        // #--- Privileges END ------------------------------------------------------------------------------------------------------------------
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();

        model.addAttribute("host", Functions.getHostName());
        model.addAttribute("host2", Arrays.toString(Functions.getHostAddresses()));
        model.addAttribute("username",principal.getName());
        model.addAttribute("authorities",authorities);
        model.addAttribute("details",details);
        model.addAttribute("userRole",userRole);

        return "_public/technicalinformation";
    }

//    private String checkPrivilage(String httpMethod,Model model, Principal principal) {
//        User user = userRepo.findByUsername(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found in DB"));
//        String userRole = user.getRole();
//      //  String httpMethod = "GET";
//        List<TechPage> techPageList = techPageRepo.findByMethodAndRole(httpMethod.toUpperCase(), userRole);
//        //        List<TechPage> techPageList2 = techPageList.stream().filter(s -> s.getRoles().equals(userRole)).collect(Collectors.toList());
//        List<TechPageTmp> techPageList2 = new ArrayList<>();
//
//        for (TechPage s : techPageList) {
//
//            techPageList2.add(new TechPageTmp(s.getName(), s.getButton(), s.getHeader(), s.getUrl()));
//        }
//
//        // Set<TechPage> techPageList2 = new HashSet<TechPage>();
//        // techPageList2.addAll(techPageList);
//
//        Optional<TechPage> techpagePrivilege = techPageRepo.findByMethodAndNameAndRole(httpMethod.toUpperCase(), this.techPage.getName(), userRole);
//        if (techpagePrivilege.isEmpty()) {
//            return null;
//        }
//        System.out.println("# techPage:" + techPage);
//        System.out.println("# techPageList2:" + techPageList2);
//        techPageList.forEach(System.out::println);
//
//        // System.out.println("#techpageRole: "+ techpagePrivilege);
//        model.addAttribute("thisTechPage", techPage);
//        model.addAttribute("techPageList", techPageList2);//przekazuje tylko te definicje stron , do których user ma uprawnienia
//        return userRole;
//    }
}
