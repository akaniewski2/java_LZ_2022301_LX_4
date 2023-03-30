package pl.arkani.LZ_2022301_LX.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.arkani.LZ_2022301_LX.model.<ClassNm>;
import pl.arkani.LZ_2022301_LX.repo.<ClassNm>Repo;


import javax.validation.Valid;


@Controller
@RequestMapping(value = "<@RequestMapping>")
public class <ClassNm>Controller {
    private <ClassNmRepo> <classNmRepo>;

    @Autowired
    public <ClassNm>Controller(<ClassNmRepo> <classNmRepo>) {
        this.<classNmRepo> = <classNmRepo>;
    }

    @GetMapping("<@GetMapping>")
    public String show(Model model) {

        model.addAttribute("<classNm>", <classNmRepo>.findAll());
        return "<htmlFolder>/<htmlFilePrefix>";


    }

    @GetMapping("<@GetMapping>/add")
    public String add(<ClassNm> <classNm> ) {
        return "<htmlFolder>/<htmlFilePrefix>-update";
    }

    @GetMapping("<@GetMapping>/edit/{id}")
    public String update(@PathVariable/*("id")*/ long id, Model model) {
        <ClassNm> <classNm> = <classNm>Repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid <classNm> Id:" + id));

        model.addAttribute("<classNm>", <classNm>);
        return "<htmlFolder>/<htmlFilePrefix>-update";
    }


    @PostMapping("<@GetMapping>/update/{id}")
    public String update(@PathVariable/*("id")*/ long id, @Valid  <ClassNm> <classNm>,
                                    BindingResult result, Model model) {


        <classNm>Repo.save(<classNm>);
        return "redirect:<@RequestMapping><@GetMapping>";
    }

    @GetMapping("<@GetMapping>/delete/{id}")
    public String delete(@PathVariable/*("id")*/ long id, Model model) {
        <ClassNm> <classNm> = <classNm>Repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid <classNm> Id:" + id));
        <classNm>Repo.delete(<classNm>);
        return "redirect:<@RequestMapping><@GetMapping>";
    }

}
