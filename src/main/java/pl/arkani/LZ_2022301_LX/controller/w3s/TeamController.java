package pl.arkani.LZ_2022301_LX.controller.w3s;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/w3s/")
public class TeamController {


    @GetMapping("/team")
    public String getTeam () {
        return "w3s/team/team";
    }
}
