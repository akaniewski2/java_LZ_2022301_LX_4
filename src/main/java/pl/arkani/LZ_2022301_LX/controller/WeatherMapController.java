//package pl.arkani.LZ_2022301_LX.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import pl.arkani.KoronowirusMapa.model.EmailSimpleSend;
//import pl.arkani.KoronowirusMapa.dane.AktualizacjaDanych;
//import pl.arkani.KoronowirusMapa.model.DateRepo;
//
//
//import javax.mail.SendFailedException;
//import java.io.IOException;
//import java.net.Inet4Address;
//
////https://www.youtube.com/watch?v=ko3ocQNmHeU
////Leaflet
////openstreetmap
//// https://stackoverflow.com/questions/47966375/how-to-create-leaflet-markers-colored-by-a-numeric-variable
////https://www.wspolrzedne.pl
//
//@Controller
//public class WeatherMapController {
//  // Confirmed confirmed= new Confirmed(new DateRepo());
//
//    private DateRepo dataRepo;
//    private String dt1;
//    private AktualizacjaDanych aktualizacjaDanych;
//
//
//    public String getDt1() {
//        return dt1;
//    }
//    public void setDt1(String dt1) {
//        this.dt1 = dt1;
//    }
//
//
//    @Autowired
//    public WeatherMapController(DateRepo dataRepo, AktualizacjaDanych aktualizacjaDanych) {
//        this.dataRepo = dataRepo;
//        this.aktualizacjaDanych = aktualizacjaDanych;
//    }
//
//
//   // HttpServletRequest httpServletRequest =null;
//    //HttpServletResponse response;
//
//    @RequestMapping(path = "/",method = RequestMethod.GET)
//    public String getStart() {
//
//        return "map";
//    }
//
//
//    @RequestMapping(path = "covid-19/mapa",method = RequestMethod.GET)
//    public String getMap(Model model, @RequestParam(required =false) String dt)  throws IOException, InterruptedException {
//
//        try {
//         //   EmailSimpleSend.emailSend("START MAPA");
//            System.out.println("#EmailSimpleSend.emailSend");
//        } catch (Exception e) {
//            System.out.println("# ERR ! : EMAIL : " + e.getMessage());
//        }
//
//
//    //    aktualizacjaDanych.AktualizacjaDanych();
//        model.addAttribute("pointList",dataRepo.getPointList());
//        model.addAttribute("point0List",dataRepo.getPoint0List());
//
//
//        System.out.println("IP:"+Inet4Address.getLocalHost().getHostAddress()) ;
//
////
////        String ipAddress = httpServletRequest.getHeader("X-FORWARDED-FOR");
////        if (ipAddress == null) {
////            ipAddress = httpServletRequest.getRemoteAddr();
////        }
//
////        System.out.println("IP: "+Funkcje.getClientIp(re));
////        System.out.println("IP: "+httpServletRequest.getRequestURL());
//
//        return "map";
//
//
//    }
//}
