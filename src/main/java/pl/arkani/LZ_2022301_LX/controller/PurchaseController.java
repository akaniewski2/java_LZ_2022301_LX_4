package pl.arkani.LZ_2022301_LX.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.arkani.LZ_2022301_LX.mapper.Mapper;
import pl.arkani.LZ_2022301_LX.model.Purchase;

import pl.arkani.LZ_2022301_LX.model.Zakupy;
import pl.arkani.LZ_2022301_LX.repo.PurchaseCategoryRepo;
import pl.arkani.LZ_2022301_LX.repo.PurchaseRepo;
import pl.arkani.LZ_2022301_LX.repo.UserRepo;
import pl.arkani.LZ_2022301_LX.repo.ZakupyRepo;
import pl.arkani.LZ_2022301_LX.service.PurchaseCategoryService;
import pl.arkani.LZ_2022301_LX.service.PurchaseService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping(value = "/arkani2/")
public class PurchaseController {





    private PurchaseRepo purchaseRepo;
    private PurchaseService purchaseService;
    private PurchaseCategoryService purchaseCategoryService;
    private PurchaseCategoryRepo purchaseCategoryRepo;
    private final UserRepo userRepo;
    private ZakupyRepo zakupyRepo;
    private Mapper mapper ;

    @Autowired
    public PurchaseController(PurchaseRepo purchaseRepo, PurchaseService purchaseService, PurchaseCategoryService purchaseCategoryService, PurchaseCategoryRepo purchaseCategoryRepo,
                              UserRepo userRepo, ZakupyRepo zakupyRepo) {
        this.purchaseRepo = purchaseRepo;
        this.purchaseService = purchaseService;
        this.purchaseCategoryService = purchaseCategoryService;
        this.purchaseCategoryRepo = purchaseCategoryRepo;
        this.userRepo = userRepo;
        this.zakupyRepo = zakupyRepo;
        this.mapper = new Mapper(this.purchaseCategoryRepo);
    }


    @GetMapping("purchase/add")
    public String showSignUpForm(Purchase purchase,Principal principal,Model model) {
        model.addAttribute("purchaseCategory", purchaseCategoryRepo.findAll());
      //  purchaseCategoryRepo.findAll().forEach(System.out::println);
        //system.out.println("# principal.getName():"+principal.getName());

        return "purchase/purchase-add";
    }

    @PostMapping("purchase/add")
    public String addPurchase(@Valid Purchase purchase, BindingResult result, Model model,Principal principal) {
      //  model.addAttribute("purchaseCategory", purchaseCategoryRepo.findAll());
        //system.out.println("# principal.getName():"+principal.getName());
        if (result.hasErrors() || purchase.getName().isBlank() ) {
            //system.out.println("1");
            return "purchase/purchase"; // lokalizacja html
        }
        //system.out.println("2");

        purchaseService.save(purchase,principal);


        //List<Zakupy> zakupyList = purchaseList.stream().map(x -> mapper.PurchaseToZakupy(x)).collect(Collectors.toList());
        Zakupy zakupy = mapper.PurchaseToZakupy(purchase);
        zakupyRepo.save(zakupy);

        //system.out.println("3");
        return "redirect:/arkani2/purchase"; //redirect przekierowuje na url
    }

    // additional CRUD methods
    @GetMapping("purchase")
    public String showPurchaseList(Model model,Principal principal) {
//        Optional<User> user = userRepo.findByUsername(principal.getName());
//        if (user.isPresent()) {
//            user.get().getAuthorities();
//        }

        model.addAttribute("purchase", purchaseService.findAllWithLimit(1000));
        model.addAttribute("username", principal.getName());

        return "purchase/purchase";
    }

    @GetMapping("purchase/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model,Principal principal) {
        model.addAttribute("purchaseCategory", purchaseCategoryRepo.findAll());
        model.addAttribute("username", principal.getName());
        Purchase purchase = purchaseRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid purchase Id:" + id));

        model.addAttribute("purchase", purchase);
        return "purchase/purchase-update";
    }

    @PostMapping("purchase/update/{id}")
    public String updatePurchase(@PathVariable("id") long id, @Valid Purchase purchase,
                             BindingResult result, Model model,Principal principal) {
    //    model.addAttribute("purchaseCategory", purchaseCategoryRepo.findAll());
        model.addAttribute("username", principal.getName());
        if (result.hasErrors()) {
            purchase.setId(id);
            return "purchase/purchase-update";
        }

        //purchase.setModBy();

        purchaseService.update(purchase,principal);
        Zakupy zakupy = mapper.PurchaseToZakupy(purchase);
        zakupyRepo.save(zakupy);
        return "redirect:/arkani2/purchase";
    }

    @GetMapping("purchase/delete/{id}")
    public String deletePurchase(@PathVariable("id") long id, Model model,Principal principal) {
        Purchase purchase = purchaseRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid purchase Id:" + id));
        //purchaseRepo.delete(purchase);

        purchaseService.delete(purchase,principal);

        return "redirect:/arkani2/purchase";
    }
}