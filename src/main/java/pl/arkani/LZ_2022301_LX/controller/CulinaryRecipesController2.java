// -- # CONTROLLER
package pl.arkani.LZ_2022301_LX.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.arkani.LZ_2022301_LX.mapper.Mapper;
import pl.arkani.LZ_2022301_LX.model.*;
import pl.arkani.LZ_2022301_LX.repo.*;
import pl.arkani.LZ_2022301_LX.service.CulinaryRecipesService;
import pl.arkani.LZ_2022301_LX.service.PurchaseService;

import java.nio.file.Paths;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
//@AllArgsConstructor
@RequestMapping(value = "/arkani2/culinaryrecipes-update2")
public class CulinaryRecipesController2 {
    private CulinaryRecipesService culinaryRecipesService;
    private CulinaryRecipesRepo culinaryRecipesRepo;
    private PurchaseCategoryRepo purchaseCategoryRepo;
    private PurchaseService purchaseService;


    private final CulinaryIngredientsRepo culinaryIngredientsRepo;

    private int licznik;
    private Message message;
    private String msg;
    private final PurchaseRepo purchaseRepo;
    private final UserRepo userRepo;
    private final ZakupyRepo zakupyRepo;


    @Autowired
    public CulinaryRecipesController2(CulinaryRecipesService culinaryRecipesService, CulinaryRecipesRepo culinaryRecipesRepo,
                                      PurchaseCategoryRepo purchaseCategoryRepo, PurchaseService purchaseService, CulinaryIngredientsRepo culinaryIngredientsRepo,
                                      PurchaseRepo purchaseRepo,
                                      UserRepo userRepo,
                                      ZakupyRepo zakupyRepo) {
        this.culinaryRecipesService = culinaryRecipesService;
        this.culinaryRecipesRepo = culinaryRecipesRepo;
        this.purchaseCategoryRepo = purchaseCategoryRepo;
        this.purchaseService = purchaseService;
        this.culinaryIngredientsRepo = culinaryIngredientsRepo;

        this.msg = "";
        this.message = new Message();
        this.licznik=0;
        this.purchaseRepo = purchaseRepo;
        this.userRepo = userRepo;
        this.zakupyRepo = zakupyRepo;
    }

    @GetMapping
    public String show(Model model) {

        var culinaryRecipesToEdit=new CulinaryRecipes();
        //culinaryRecipesToEdit.setName("test");
       // culinaryRecipesToEdit.setDescription("test");
        List<CulinaryIngredients> culinaryIngredientsList = new ArrayList<>();
        culinaryIngredientsList.add(new CulinaryIngredients());

        culinaryRecipesToEdit.setCulinaryIngredients(culinaryIngredientsList);
        System.out.println("#culinaryRecipesToEdit.getCulinaryIngredients():"+ culinaryRecipesToEdit.getCulinaryIngredients());
        model.addAttribute("culinaryRecipes",culinaryRecipesToEdit);

        model.addAttribute("UPLOAD_DIRECTORY",   UploadImageController.UPLOAD_DIRECTORY);
       // model.addAttribute("culinaryRecipesList", culinaryRecipesRepo.findAll());
        return "culinaryrecipes/culinaryrecipes-update2";


    }

    @PostMapping
    String addCulinaryRecipes(@ModelAttribute("culinaryRecipes")  CulinaryRecipes culinaryRecipes,Model model) {

        //# todo: do wyjasnienia jak to inaczej zrobic
        culinaryRecipesService.saveWithIngredients(culinaryRecipes);

        model.addAttribute("culinaryRecipes",culinaryRecipes);
        model.addAttribute("messageStr","Element został dodany");
        return "redirect:/arkani2/culinaryrecipes";


    }

    @PutMapping("/update")
    String updateCulinaryRecipes(@ModelAttribute("culinaryRecipes")  CulinaryRecipes culinaryRecipes,Model model,Principal principal) {

        //# todo: do wyjasnienia jak to inaczej zrobic
        culinaryRecipesService.saveWithIngredients(culinaryRecipes);

        //model.addAttribute("user",principal.getName());
        model.addAttribute("culinaryRecipes",culinaryRecipes);
        model.addAttribute("message","Element został dodany");
        return "redirect:/arkani2/culinaryrecipes";


    }

    //!!! dynamic method by bind by name param in html  !!!
    @PostMapping(params="addStep") //w html params to znacznik "name"
    String addCulinaryRecipesStep(@ModelAttribute("culinaryRecipes") CulinaryRecipes culinaryRecipes)  {
        culinaryRecipes.getCulinaryIngredients().add(new CulinaryIngredients());
        System.out.println("#culinaryRecipes:"+culinaryRecipes);

        return "culinaryrecipes/culinaryrecipes-update2";
    }


    //!!! dynamic method by bind by name param in html  !!!
    @PostMapping(params="addIngredientsToPurchase") //w html params to znacznik "name"
    String   addIngredientsToPurchase(@ModelAttribute("culinaryRecipes") CulinaryRecipes culinaryRecipes, Model model, Message message, Principal principal) throws Exception {

        System.out.println("#message:"+message);
        licznik++;
        message.setCode(0);
        message.setMsg("licznik:"+licznik);
        model.addAttribute("licznik",licznik);

       // String response = URLExecutor.executeURL("http://192.168.1.5/genericArgs?p=1153114845");
       // System.out.println(response);

        // culinaryRecipes.getCulinaryIngredients().add(new CulinaryIngredients());
        System.out.println("#culinaryRecipes:"+culinaryRecipes);
        System.out.println("#culinaryRecipes:"+culinaryRecipes.getCulinaryIngredients());

        Mapper mapper = new Mapper(this.purchaseCategoryRepo);
        List<Purchase> purchaseList = culinaryRecipes.getCulinaryIngredients().stream().map(x -> mapper.CulinaryIngredientsToPurchase(culinaryRecipes,x)).collect(Collectors.toList());


        Optional<User> user =userRepo.findByUsername(principal.getName());
        for (Purchase p: purchaseList) {
           // p.setUser(user.get());

            purchaseService.save(p,principal);
        }

        List<Zakupy> zakupyList = purchaseList.stream().map(x -> mapper.PurchaseToZakupy(x)).collect(Collectors.toList());
        zakupyRepo.saveAll(zakupyList);

        return "culinaryrecipes/culinaryrecipes-update2";
    }




    @GetMapping("/edit/{id}")
    public String updateGoodsRating(@PathVariable/*("id")*/ long id, Model model) {
        model.addAttribute("culinaryRecipes",new CulinaryRecipes());
        CulinaryRecipes culinaryRecipes = culinaryRecipesRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid goodsRating Id:" + id));
        CulinaryIngredients culinaryIngredients= culinaryIngredientsRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid goodsRating Id:" + id));

          model.addAttribute("UPLOAD_DIRECTORY", UploadImageController.UPLOAD_DIRECTORY);
          model.addAttribute("culinaryRecipes", culinaryRecipes);
          model.addAttribute("culinaryIngredients", culinaryIngredients);
        return "culinaryrecipes/culinaryrecipes-update2";
    }

    @ModelAttribute
    List<CulinaryRecipes> getCulinaryRecipes() {
        return culinaryRecipesRepo.findAll();
    }
    @ModelAttribute("message")
    Message getMessage() {
        return message;
    }

//    @PostMapping("goodsrating/update/{id}")
//    public String updateGoodsRating(@PathVariable/*("id")*/ long id, @Valid @ModelAttribute("goodsRating") GoodsRating goodsRating,
//                                    BindingResult result, Model model) {
//
//
//        //    String err = goodsRatingValidationService.validateGoods(goodsRating);
////        if (!err.isEmpty()) {
////            ObjectError error = new ObjectError("globalError", err);
////            result.addError(error);
////        }
//
//        if (result.hasErrors()||goodsRating.getItem().isEmpty()  ) {
//            result.getAllErrors().forEach(e->System.out.println(e.getObjectName() +" "+ e.getDefaultMessage()) );
//            return "goodsrating/goodsrating-update"; // lokalizacja html
//        }
//
//    //    goodsRatingRepo.save(goodsRating);
//        return "redirect:/arkani2/goodsrating";
//    }


}
