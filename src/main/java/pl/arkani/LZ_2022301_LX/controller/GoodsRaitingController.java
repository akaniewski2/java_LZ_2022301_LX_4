package pl.arkani.LZ_2022301_LX.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import pl.arkani.LZ_2022301_LX.Examples.SqlRepoExec;
import pl.arkani.LZ_2022301_LX.model.GoodsRating;
import pl.arkani.LZ_2022301_LX.repo.GoodsRatingRepo;
import pl.arkani.LZ_2022301_LX.repo.TechPageRepo;
import pl.arkani.LZ_2022301_LX.service.GoodsRatingValidationService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/arkani2/")
public class GoodsRaitingController {

    private GoodsRatingValidationService goodsRatingValidationService;
    private GoodsRatingRepo goodsRatingRepo;
    private TechPageRepo techPageRepo;
    private SqlRepoExec sqlRepoExec;
    @Autowired
    public GoodsRaitingController(GoodsRatingValidationService goodsRatingValidationService, GoodsRatingRepo goodsRatingRepo, TechPageRepo techPageRepo, SqlRepoExec sqlRepoExec) {
        this.goodsRatingValidationService = goodsRatingValidationService;
        this.goodsRatingRepo = goodsRatingRepo;
        this.techPageRepo = techPageRepo;
        this.sqlRepoExec = sqlRepoExec;
    }

    // additional CRUD methods
    @GetMapping("goodsrating")
    public String showGoodsRatingList(Model model) {



        //sqlRepoExec.getTableHeaders("user");
        List<String> headers = sqlRepoExec.getTableHeaders("goods_rating"); // Arrays.asList("id", "username", "role");
        List<List<String>> rows= sqlRepoExec.getTableData("select * from arkani_1.goods_rating order by id");


        model.addAttribute("goodsRating", goodsRatingRepo.findAll());
        model.addAttribute("headers",headers);
        model.addAttribute("rows", rows);
        return "goodsrating/goodsrating";


    }


//    pobieranie po ${'/arkani2/goodsrating?id='+s.id}
    //cos tu nie bangla ,ale przyklad jak generowac sciezke w html jest
    @GetMapping("goodsratingitem")
    public String showGoodsRatingItem(@RequestParam/*("id")*/ long id, Model model) {

        try {

            model.addAttribute("goodsRating", goodsRatingRepo.findById(id));
            return "goodsrating/goodsrating";
        } finally {
            return "_public/error";
        }

    }


    @GetMapping("goodsrating/add")
    public String addGoodsRating(Model model) {
        model.addAttribute("goodsRating",new GoodsRating());

        return "goodsrating/goodsrating-update";
    }

//    @PostMapping("goodsrating/add")
//    public String addGoodsRating(@Valid GoodsRating goodsRating, BindingResult result, Model model) {
//        if (result.hasErrors() ) {
//            result.getAllErrors().forEach(e->System.out.println(e.getObjectName() +" "+ e.getDefaultMessage()) );
//            return "goodsrating/goodsrating-update"; // lokalizacja html
//        }
//        //system.out.println("2");
//        goodsRatingRepo.save(goodsRating);
//        //system.out.println("3");
//        return "redirect:/arkani2/goodsrating"; //redirect przekierowuje na url
//    }


    @GetMapping("goodsrating/edit/{id}")
    public String updateGoodsRating(@PathVariable/*("id")*/ long id, Model model) {
        model.addAttribute("goodsRating",new GoodsRating());
        GoodsRating goodsRating = goodsRatingRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid goodsRating Id:" + id));

      //  model.addAttribute("goodsRating", goodsRating);
        return "goodsrating/goodsrating-update";
    }

    @PostMapping("goodsrating/update/{id}")
    public String updateGoodsRating(@PathVariable/*("id")*/ long id, @Valid @ModelAttribute("goodsRating") GoodsRating goodsRating,
                             BindingResult result, Model model) {


    //    String err = goodsRatingValidationService.validateGoods(goodsRating);
//        if (!err.isEmpty()) {
//            ObjectError error = new ObjectError("globalError", err);
//            result.addError(error);
//        }

        if (result.hasErrors()||goodsRating.getItem().isEmpty()  ) {
        result.getAllErrors().forEach(e->System.out.println(e.getObjectName() +" "+ e.getDefaultMessage()) );
        return "goodsrating/goodsrating-update"; // lokalizacja html
        }

        goodsRatingRepo.save(goodsRating);
        return "redirect:/arkani2/goodsrating";
    }

    @GetMapping("goodsrating/delete/{id}")
    public String deleteGoodsRating(@PathVariable/*("id")*/ long id, Model model) {
        GoodsRating goodsRating = goodsRatingRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid goodsRating Id:" + id));
        goodsRatingRepo.delete(goodsRating);
        return "redirect:/arkani2/goodsrating";
    }



}