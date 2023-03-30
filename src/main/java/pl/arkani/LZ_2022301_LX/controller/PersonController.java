package pl.arkani.LZ_2022301_LX.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.arkani.LZ_2022301_LX.model.Person;
import pl.arkani.LZ_2022301_LX.repo.PersonRepository;

import javax.validation.Valid;


@Controller
@RequestMapping(value = "/test/person")
public class PersonController {
    private PersonRepository repository;
    @GetMapping("/add")
    public String showAddPersonForm(Person person,Model model) {
       // model.addAttribute("person",new Person());
        return "person/person-add";
    }

    @PostMapping("/add")
    public String addPerson(@Valid Person person, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "person/person-add";
        }
        repository.save(person);
        return "redirect:/home";
    }

}
