package pl.arkani.LZ_2022301_LX.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import pl.arkani.LZ_2022301_LX.model.GoodsRating;
import pl.arkani.LZ_2022301_LX.model.ImportantDates;
import pl.arkani.LZ_2022301_LX.model.Person;
import pl.arkani.LZ_2022301_LX.repo.ImportantDatesRepo;
import pl.arkani.LZ_2022301_LX.repo.PersonRepository;

import javax.validation.Valid;


@Controller
@RequestMapping(value = "/arkani2/")
public class ImportantDatesController {
    private ImportantDatesRepo importantDatesRepo;

    @Autowired
    public ImportantDatesController(ImportantDatesRepo importantDatesRepo) {
        this.importantDatesRepo = importantDatesRepo;
    }

    @GetMapping("importantdates")
    public String show(Model model) {

        model.addAttribute("importantDates", importantDatesRepo.findAll());
        return "importantdates/importantdates";


    }

    @GetMapping("importantdates/add")
    public String add(ImportantDates importantDates ) {
       // model.addAttribute("importantDates",new ImportantDates());
        return "importantdates/importantdates-update";
    }

    @GetMapping("importantdates/edit/{id}")
    public String update(@PathVariable/*("id")*/ long id, Model model) {
        ImportantDates importantDates = importantDatesRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Id:" + id));

        model.addAttribute("importantDates", importantDates);
        return "importantdates/importantdates-update";
    }


    @PostMapping("importantdates/update/{id}")
    public String update(@PathVariable/*("id")*/ long id, @Valid  ImportantDates importantDates,
                                    BindingResult result, Model model) {


        importantDatesRepo.save(importantDates);
        return "redirect:/arkani2/importantdates";
    }

    @GetMapping("importantdates/delete/{id}")
    public String delete(@PathVariable/*("id")*/ long id, Model model) {
        ImportantDates importantDates = importantDatesRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid goodsRating Id:" + id));
        importantDatesRepo.delete(importantDates);
        return "redirect:/arkani2/importantdates";
    }

}
