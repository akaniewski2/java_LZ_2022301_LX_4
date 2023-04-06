package pl.arkani.LZ_2022301_LX.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import pl.arkani.LZ_2022301_LX.model.TechPage;
import pl.arkani.LZ_2022301_LX.model.TechPageTmp;
import pl.arkani.LZ_2022301_LX.model.User;
import pl.arkani.LZ_2022301_LX.repo.*;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping(value = "/arkani2/")
public class TvChannelController {

    private UserRepo userRepo;
    private TvChannelRepo tvChannelRepo;
    private TvRemoteRepo tvRemoteRepo;

    private TechPageRepo techPageRepo;

    private TechPage techPage;
    private final TestRepo testRepo;

    @Autowired
    public TvChannelController(UserRepo userRepo, TvChannelRepo tvChannelRepo, TvRemoteRepo tvRemoteRepo, TechPageRepo techPageRepo,
                               TestRepo testRepo) {
        this.userRepo = userRepo;
        this.tvChannelRepo = tvChannelRepo;
        this.tvRemoteRepo = tvRemoteRepo;
        this.techPageRepo = techPageRepo;

        this.techPage = techPageRepo.findByName("tv_channels"); // definiuje raz strone na której dotyczy kontroler
        this.testRepo = testRepo;
    }

        @GetMapping(path = "/a")
        public ModelAndView getTestData() {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("welcome");
            mv.getModel().put("data", "Welcome home man");

            return mv;
        }



    @RequestMapping(path = "tv_channels",method = RequestMethod.GET)
    public String tv_channels(Model model, @RequestParam(required =false) String dt, Principal principal)  {

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


        model.addAttribute("tvChannels",tvChannelRepo.findAll());
        model.addAttribute("tvRemotes",tvRemoteRepo.findAll());



        try {
            System.out.println("IP:"+ Inet4Address.getLocalHost().getHostAddress()) ;
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        //-- OPTIONAL -----------------------------------------------------------------------------

        System.out.println("Optional #1 of"+Optional.of(techpagePrivilege.get().getName())); // zakłada ze optional nigdy nie bedzie nullem
        System.out.println("Optional #2 ofNullable  "+Optional.ofNullable(techpagePrivilege.get().getName())); // zakłada ze optional nigdy nie bedzie nullem

        System.out.println("-- # OPTIONAL ---");
        if (techpagePrivilege.isPresent() ) {

            System.out.println(techpagePrivilege.get().getName());
        }


        //------------------------------------------------------------------------------------------

        return "tv/tv_channels";


    }


    @RequestMapping(path = "tv_channels_test",method = RequestMethod.GET)
    public String getMap(Model model, @RequestParam(required =false) String dt)  {

//        try {
//            EmailSimpleSend.emailSend("START MAPA");
//        } catch (Exception e) {
//            //system.out.println("# ERR ! : EMAIL : " + e.getMessage());
//        }



        model.addAttribute("tvChannels",tvChannelRepo.findAll());
        //model.addAttribute("pointList",tvChannelRepo.findAll());
       // model.addAttribute("point0List",dataRepo.getPoint0List());


        try {
            System.out.println("IP:"+ Inet4Address.getLocalHost().getHostAddress()) ;
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

//
//        String ipAddress = httpServletRequest.getHeader("X-FORWARDED-FOR");
//        if (ipAddress == null) {
//            ipAddress = httpServletRequest.getRemoteAddr();
//        }

//        //system.out.println("IP: "+Funkcje.getClientIp(re));
//        //system.out.println("IP: "+httpServletRequest.getRequestURL());

        return "tv_channels_test";


    }
}


