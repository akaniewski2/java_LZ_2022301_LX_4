package pl.arkani.LZ_2022301_LX.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.arkani.LZ_2022301_LX.model.Music;
import pl.arkani.LZ_2022301_LX.model.TechPage;
import pl.arkani.LZ_2022301_LX.model.TechPageTmp;
import pl.arkani.LZ_2022301_LX.model.User;
import pl.arkani.LZ_2022301_LX.repo.MusicRepo;
import pl.arkani.LZ_2022301_LX.repo.TechPageRepo;
import pl.arkani.LZ_2022301_LX.repo.UserRepo;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/arkani2/")
public class MusicController {


    private MusicRepo musicRepo;
    private TechPageRepo techPageRepo;

    private TechPage techPage;
    private UserRepo userRepo;

    @Autowired
    public MusicController(MusicRepo musicRepo, TechPageRepo techPageRepo, UserRepo userRepo) {
        this.musicRepo = musicRepo;
        this.techPageRepo = techPageRepo;
        this.techPage = techPageRepo.findByName("music");
        this.userRepo = userRepo;
    }

    @GetMapping("music/add")
    public String showSignUpForm(Music music) {
        return "music/music-add";
    }

    @PostMapping("music/add")
    public String addMusic(@Valid Music music, BindingResult result, Model model) {
        if (result.hasErrors() || music.getAuthor().isBlank() ) {
            //system.out.println("1");
            return "music/music-add"; // lokalizacja html
        }
        //system.out.println("2");
        musicRepo.save(music);
        //system.out.println("3");
        return "redirect:/arkani2/music"; //redirect przekierowuje na url
    }

    // additional CRUD methods
    @GetMapping("music")
    public String showMusicList(Model model, Principal principal) {

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
        if (techpagePrivilege.isEmpty()) {return "/home";}
        System.out.println("# techPage:" + techPage);
        System.out.println("# techPageList2:" + techPageList2);
        techPageList.forEach(System.out::println);

        // System.out.println("#techpageRole: "+ techpagePrivilege);
        model.addAttribute("thisTechPage",techPage);
        model.addAttribute("techPageList",techPageList2);//przekazuje tylko te definicje stron , do których user ma uprawnienia
        // #--- Privileges END ------------------------------------------------------------------------------------------------------------------

        model.addAttribute("music", musicRepo.findAll());
        model.addAttribute("techPage", techPageRepo.findAll());
        return "music/music";
    }

    @GetMapping("music/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Music music = musicRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid music Id:" + id));

        model.addAttribute("music", music);
        return "music/music-update";
    }

    @PostMapping("music/update/{id}")
    public String updateMusic(@PathVariable("id") long id, @Valid Music music,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            music.setId(id);
            return "music/music-update";
        }

        musicRepo.save(music);
        return "redirect:/arkani2/music";
    }

    @GetMapping("music/delete/{id}")
    public String deleteMusic(@PathVariable("id") long id, Model model) {
        Music music = musicRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid music Id:" + id));
        musicRepo.delete(music);
        return "redirect:/arkani2/music";
    }
}