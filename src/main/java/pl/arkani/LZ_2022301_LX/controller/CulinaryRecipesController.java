// -- # CONTROLLER
package pl.arkani.LZ_2022301_LX.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.arkani.LZ_2022301_LX.model.CulinaryRecipes;
import pl.arkani.LZ_2022301_LX.repo.CulinaryRecipesRepo;


import javax.validation.Valid;


@Controller
@RequestMapping(value = "/arkani2/")
public class CulinaryRecipesController {
    private CulinaryRecipesRepo culinaryRecipesRepo;

    @Autowired
    public CulinaryRecipesController(CulinaryRecipesRepo culinaryRecipesRepo) {
        this.culinaryRecipesRepo = culinaryRecipesRepo;
    }

    @GetMapping("culinaryrecipes")
    public String show(Model model) {

        model.addAttribute("culinaryRecipes", culinaryRecipesRepo.findAll());
        return "culinaryrecipes/culinaryrecipes";


    }

    @GetMapping("culinaryrecipes/add")
    public String add(CulinaryRecipes culinaryRecipes ) {
        return "culinaryrecipes/culinaryrecipes-update";
    }

    @GetMapping("culinaryrecipes/edit/{id}")
    public String update(@PathVariable/*("id")*/ long id, Model model) {
        CulinaryRecipes culinaryRecipes = culinaryRecipesRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid culinaryRecipes Id:" + id));

        model.addAttribute("culinaryRecipes", culinaryRecipes);
        return "culinaryrecipes/culinaryrecipes-update";
    }


    @PostMapping("culinaryrecipes/update/{id}")
    public String update(@PathVariable/*("id")*/ long id, @Valid  CulinaryRecipes culinaryRecipes,
                                    BindingResult result, Model model) {


        culinaryRecipesRepo.save(culinaryRecipes);
        return "redirect:/arkani2/culinaryrecipes";
    }

    @GetMapping("culinaryrecipes/delete/{id}")
    public String delete(@PathVariable/*("id")*/ long id, Model model) {
        CulinaryRecipes culinaryRecipes = culinaryRecipesRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid culinaryRecipes Id:" + id));
        culinaryRecipesRepo.delete(culinaryRecipes);
        return "redirect:/arkani2/culinaryrecipes";
    }

}
