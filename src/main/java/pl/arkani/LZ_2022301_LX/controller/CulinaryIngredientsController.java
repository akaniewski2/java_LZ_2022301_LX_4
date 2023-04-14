package pl.arkani.LZ_2022301_LX.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.arkani.LZ_2022301_LX.model.CulinaryIngredients;
import pl.arkani.LZ_2022301_LX.repo.CulinaryIngredientsRepo;


import javax.validation.Valid;


@Controller
@RequestMapping(value = "/arkani2/")
public class CulinaryIngredientsController {
    private CulinaryIngredientsRepo culinaryIngredientsRepo;

    @Autowired
    public CulinaryIngredientsController(CulinaryIngredientsRepo culinaryIngredientsRepo) {
        this.culinaryIngredientsRepo = culinaryIngredientsRepo;
    }

    @GetMapping("culinaryingredients")
    public String show(Model model) {

        model.addAttribute("culinaryIngredients", culinaryIngredientsRepo.findAll());
        return "culinaryingredients/culinaryingredients";


    }

    @GetMapping("culinaryingredients/add")
    public String add(CulinaryIngredients culinaryIngredients ) {
        return "culinaryingredients/culinaryingredients-update";
    }

    @GetMapping("culinaryingredients/edit/{id}")
    public String update(@PathVariable/*("id")*/ long id, Model model) {
        CulinaryIngredients culinaryIngredients = culinaryIngredientsRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid culinaryIngredients Id:" + id));

        model.addAttribute("culinaryIngredients", culinaryIngredients);
        return "culinaryingredients/culinaryingredients-update";
    }


    @PostMapping("culinaryingredients/update/{id}")
    public String update(@PathVariable/*("id")*/ long id, @Valid  CulinaryIngredients culinaryIngredients,
                                    BindingResult result, Model model) {


        culinaryIngredientsRepo.save(culinaryIngredients);
        return "redirect:/arkani2/culinaryingredients";
    }

    @GetMapping("culinaryingredients/delete/{id}")
    public String delete(@PathVariable/*("id")*/ long id, Model model) {
        CulinaryIngredients culinaryIngredients = culinaryIngredientsRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid culinaryIngredients Id:" + id));
        culinaryIngredientsRepo.delete(culinaryIngredients);
        return "redirect:/arkani2/culinaryingredients";
    }

}