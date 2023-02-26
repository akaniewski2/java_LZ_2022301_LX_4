package pl.arkani.LZ_2022301_LX.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import pl.arkani.LZ_2022301_LX.repo.TvChannelRepo;
import pl.arkani.LZ_2022301_LX.repo.TvRemoteRepo;

import java.net.Inet4Address;
import java.net.UnknownHostException;

@Controller
@RequestMapping(value = "/arkani2/")
public class TvChannelController {

    private TvChannelRepo tvChannelRepo;
    private TvRemoteRepo tvRemoteRepo;
    @Autowired
    public TvChannelController(TvChannelRepo tvChannelRepo, TvRemoteRepo tvRemoteRepo) {
        this.tvChannelRepo = tvChannelRepo;
        this.tvRemoteRepo = tvRemoteRepo;
    }

        @GetMapping(path = "/a")
        public ModelAndView getTestData() {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("welcome");
            mv.getModel().put("data", "Welcome home man");

            return mv;
        }



    @RequestMapping(path = "tv_channels",method = RequestMethod.GET)
    public String tv_channels(Model model, @RequestParam(required =false) String dt)  {



        model.addAttribute("tvChannels",tvChannelRepo.findAll());
        model.addAttribute("tvRemotes",tvRemoteRepo.findAll());



        try {
            System.out.println("IP:"+ Inet4Address.getLocalHost().getHostAddress()) ;
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        return "tv_channels";


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


