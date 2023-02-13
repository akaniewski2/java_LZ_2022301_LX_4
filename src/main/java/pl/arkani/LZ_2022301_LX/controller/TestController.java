package pl.arkani.LZ_2022301_LX.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.arkani.LZ_2022301_LX.model.Test;
import pl.arkani.LZ_2022301_LX.repo.TestRepo;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/arkani2/")
public class TestController {


    private TestRepo testRepo;
    @Autowired
    public TestController(TestRepo testRepo) {
        this.testRepo = testRepo;
    }

    @GetMapping("test/add")
    public String showSignUpForm(Test test) {
        return "test/test-add";
    }
    
    @PostMapping("test/add")
    public String addTest(@Valid Test test, BindingResult result, Model model) {
        if (result.hasErrors() || test.getName().isBlank() ) {
            System.out.println("1");
            return "test/test-add"; // lokalizacja html
        }
        System.out.println("2");
        testRepo.save(test);
        System.out.println("3");
        return "redirect:/arkani2/test"; //redirect przekierowuje na url
    }

    // additional CRUD methods
    @GetMapping("test")
    public String showTestList(Model model) {
        model.addAttribute("test", testRepo.findAll());
        return "test/test";
    }

    @GetMapping("test/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Test test = testRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid test Id:" + id));

        model.addAttribute("test", test);
        return "test/test-update";
    }

    @PostMapping("test/update/{id}")
    public String updateTest(@PathVariable("id") long id, @Valid Test test,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            test.setId(id);
            return "test/test-update";
        }

        testRepo.save(test);
        return "redirect:/arkani2/test";
    }

    @GetMapping("test/delete/{id}")
    public String deleteTest(@PathVariable("id") long id, Model model) {
        Test test = testRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid test Id:" + id));
        testRepo.delete(test);
        return "redirect:/arkani2/test";
    }
}