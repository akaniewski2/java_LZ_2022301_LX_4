package pl.arkani.LZ_2022301_LX.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.arkani.LZ_2022301_LX.Examples.SqlRepo;
import pl.arkani.LZ_2022301_LX.Examples.SqlRepoExec;
import pl.arkani.LZ_2022301_LX.model.Test;
import pl.arkani.LZ_2022301_LX.repo.TestRepo;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/arkani2/")
public class TestController {


    private TestRepo testRepo;
    private SqlRepoExec sqlRepoExec;
    @Autowired
    public TestController(TestRepo testRepo, SqlRepoExec sqlRepoExec) {
        this.testRepo = testRepo;
        this.sqlRepoExec = sqlRepoExec;
    }

    @GetMapping("test/add")
    public String showSignUpForm(Test test) {
        return "test/test-add";
    }
    
    @PostMapping("test/add")
    public String addTest(@Valid Test test, BindingResult result, Model model) {
        if (result.hasErrors() || test.getName().isBlank() ) {
            //system.out.println("1."+result.getAllErrors());
            return "test/test-add"; // lokalizacja html
        }
        //system.out.println("2");
        testRepo.save(test);
        //system.out.println("3");
        return "redirect:/arkani2/test"; //redirect przekierowuje na url
    }

    // additional CRUD methods
    @GetMapping("test")
    public String showTestList(Model model) {

        List<String> headers = Arrays.asList("ID", "Name", "Salary", "Status");
        List<Map<String, Object>> rows2 = new ArrayList<>();
        rows2.add(Map.of("ID", "1", "Name", "Jim", "Salary", "50000", "Status", "active"));
        rows2.add(Map.of("ID", "2", "Name", "Sally", "Salary", "50000", "Status", "inactive"));

        for (Test  t: testRepo.findAll()) {

            rows2.add(Map.of(headers.get(0),t.getId(),headers.get(1),t.getName(),headers.get(2),"50",headers.get(3),"active"));
         //   rows.add(Map.of(headers.get(0),t.getId()+10));
            rows2.add(Map.of(headers.get(1),t.getName()));
            rows2.add(Map.of(headers.get(3),"no active"));

        }


        /*List<String> */headers = Arrays.asList("id", "username", "role");
        List<List<String>> rows= sqlRepoExec.sqlRepoExecTest("select id,username,role from arkani_1.user order by id");


        model.addAttribute("test", testRepo.findAll());
        model.addAttribute("headers",headers);

        model.addAttribute("headers",headers);
        model.addAttribute("rows2", rows2);
        model.addAttribute("rows", rows);
        return "test/test";

//
//        List<Test> listOfClass = new ArrayList<>();
//
//
//
//        ArrayList<ArrayList<String>> listOfLists = new ArrayList<ArrayList<String>>();
//        ArrayList<String> singleList = new ArrayList<String>();
//        singleList.add("hello");
//        singleList.add("world");
//        listOfLists.add(singleList);
//
//        ArrayList<String> anotherList = new ArrayList<String>();
//        anotherList.add("this is another list");
//        listOfLists.add(anotherList);






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