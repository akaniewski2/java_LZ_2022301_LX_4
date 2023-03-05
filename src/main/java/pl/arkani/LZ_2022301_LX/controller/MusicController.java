package pl.arkani.LZ_2022301_LX.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.arkani.LZ_2022301_LX.model.Music;
import pl.arkani.LZ_2022301_LX.repo.MusicRepo;
import pl.arkani.LZ_2022301_LX.repo.TechPageRepo;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/arkani2/")
public class MusicController {


    private MusicRepo musicRepo;
    private TechPageRepo techPageRepo;
    @Autowired
    public MusicController(MusicRepo musicRepo, TechPageRepo techPageRepo) {
        this.musicRepo = musicRepo;
        this.techPageRepo = techPageRepo;
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
    public String showMusicList(Model model) {
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