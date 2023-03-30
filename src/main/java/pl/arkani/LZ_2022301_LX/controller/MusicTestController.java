package pl.arkani.LZ_2022301_LX.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.arkani.LZ_2022301_LX.model.MusicTest;
import pl.arkani.LZ_2022301_LX.repo.MusicTestRepo;


import javax.validation.Valid;


@Controller
@RequestMapping(value = "/arkani2/")
public class MusicTestController {
    private MusicTestRepo musicTestRepo;

    @Autowired
    public MusicTestController(MusicTestRepo musicTestRepo) {
        this.musicTestRepo = musicTestRepo;
    }

    @GetMapping("musictest")
    public String show(Model model) {

        model.addAttribute("musicTest", musicTestRepo.findAll());
        return "musictest/musictest";


    }

    @GetMapping("musictest/add")
    public String add(MusicTest musicTest ) {
        return "musictest/musictest-update";
    }

    @GetMapping("musictest/edit/{id}")
    public String update(@PathVariable/*("id")*/ long id, Model model) {
        MusicTest musicTest = musicTestRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid musicTest Id:" + id));

        model.addAttribute("musicTest", musicTest);
        return "musictest/musictest-update";
    }


    @PostMapping("musictest/update/{id}")
    public String update(@PathVariable/*("id")*/ long id, @Valid  MusicTest musicTest,
                         BindingResult result, Model model) {


        musicTestRepo.save(musicTest);
        return "redirect:/arkani2/musictest";
    }

    @GetMapping("musictest/delete/{id}")
    public String delete(@PathVariable/*("id")*/ long id, Model model) {
        MusicTest musicTest = musicTestRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid musicTest Id:" + id));
        musicTestRepo.delete(musicTest);
        return "redirect:/arkani2/musictest";
    }

}