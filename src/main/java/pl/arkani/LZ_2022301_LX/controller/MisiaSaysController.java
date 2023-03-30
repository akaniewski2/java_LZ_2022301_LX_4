package pl.arkani.LZ_2022301_LX.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.arkani.LZ_2022301_LX.Examples.SqlRepoExec;
import pl.arkani.LZ_2022301_LX.model.MisiaSays;
import pl.arkani.LZ_2022301_LX.repo.MisiaSaysRepo;
import pl.arkani.LZ_2022301_LX.repo.TechPageRepo;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/arkani2/")
public class MisiaSaysController {


    private MisiaSaysRepo misiaSaysRepo;
    private TechPageRepo techPageRepo;
    private SqlRepoExec sqlRepoExec;
    @Autowired
    public MisiaSaysController(MisiaSaysRepo misiaSaysRepo, TechPageRepo techPageRepo, SqlRepoExec sqlRepoExec) {
        this.misiaSaysRepo = misiaSaysRepo;
        this.techPageRepo = techPageRepo;
        this.sqlRepoExec = sqlRepoExec;
    }

    // additional CRUD methods
    @GetMapping("misiasays")
    public String showMisiaSaysList(Model model) {



        //sqlRepoExec.getTableHeaders("user");
        List<String> headers = sqlRepoExec.getTableHeaders("misia_says"); // Arrays.asList("id", "username", "role");
        List<List<String>> rows= sqlRepoExec.getTableData("select * from arkani_1.misia_says order by id");


        model.addAttribute("misiaSays", misiaSaysRepo.findAll());
        model.addAttribute("headers",headers);
        model.addAttribute("rows", rows);
        return "misiasays/misiasays";





    }


    @GetMapping("misiasays/add")
    public String showSignUpForm(MisiaSays misiaSays) {
        return "misiasays/misiasays-update";
    }

    @PostMapping("misiasays/add")
    public String addMisiaSays(@Valid MisiaSays misiaSays, BindingResult result, Model model) {
        if (result.hasErrors() || misiaSays.getPhrase().isBlank() ) {
            //system.out.println("1."+result.getAllErrors());
            return "misiasays/misiasays-update"; // lokalizacja html
        }
        //system.out.println("2");
        misiaSaysRepo.save(misiaSays);
        //system.out.println("3");
        return "redirect:/arkani2/misiasays"; //redirect przekierowuje na url
    }


    @GetMapping("misiasays/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        MisiaSays misiaSays = misiaSaysRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid misiaSays Id:" + id));

        model.addAttribute("misiaSays", misiaSays);
        return "misiasays/misiasays-update";
    }

    @PostMapping("misiasays/update/{id}")
    public String updateMisiaSays(@PathVariable("id") long id, @Valid MisiaSays misiaSays,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            misiaSays.setId(id);
            return "misiasays/misiasays-update";
        }

        misiaSaysRepo.save(misiaSays);
        return "redirect:/arkani2/misiasays";
    }

    @GetMapping("misiasays/delete/{id}")
    public String deleteMisiaSays(@PathVariable("id") long id, Model model) {
        MisiaSays misiaSays = misiaSaysRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid misiaSays Id:" + id));
        misiaSaysRepo.delete(misiaSays);
        return "redirect:/arkani2/misiasays";
    }



}