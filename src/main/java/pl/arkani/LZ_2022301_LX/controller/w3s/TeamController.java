package pl.arkani.LZ_2022301_LX.controller.w3s;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.arkani.LZ_2022301_LX.model.SimpleEmail;
import pl.arkani.LZ_2022301_LX.service.MailService;

@Controller
@RequestMapping(value = "/w3/")
public class TeamController {

    private MailService mailService;

    @Autowired
    public TeamController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("/team")
    public String getTeam (SimpleEmail simpleEmail) {
        return "w3/team/team";
    }

    @PostMapping( params="sendemail" ,value = "/team/sendemail")
    public String sendEmail(SimpleEmail simpleEmail){

        System.out.println(simpleEmail);
        try {
            mailService.sendMail(
                    "arek.kaniewski@gmail.com",
                    //"tech.email.phone@gmail.com",
                    simpleEmail.getEmail(),
                    "Team email from: " + simpleEmail.getName(), simpleEmail.getMessage(), simpleEmail.isHtmlFormat());

        } catch (jakarta.mail.MessagingException e) {
            throw new RuntimeException(e);
        }
        return "w3/team/team";
    }
}
