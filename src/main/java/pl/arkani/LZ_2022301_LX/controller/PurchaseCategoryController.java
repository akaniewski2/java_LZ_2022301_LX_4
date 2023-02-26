package pl.arkani.LZ_2022301_LX.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.arkani.LZ_2022301_LX.model.PurchaseCategory;
import pl.arkani.LZ_2022301_LX.repo.PurchaseCategoryRepo;
import pl.arkani.LZ_2022301_LX.service.PurchaseCategoryService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping(value = "/arkani2/")
public class PurchaseCategoryController {





    private PurchaseCategoryRepo purchaseCategoryRepo;
    private PurchaseCategoryService purchaseCategoryService;

    @Autowired
    public PurchaseCategoryController(PurchaseCategoryRepo purchaseCategoryRepo, PurchaseCategoryService purchaseCategoryService) {
        this.purchaseCategoryRepo = purchaseCategoryRepo;
        this.purchaseCategoryService = purchaseCategoryService;
    }


    @GetMapping("purchaseCategory/add")
    public String showSignUpForm(PurchaseCategory purchaseCategory,Principal principal) {
        //system.out.println("# principal.getName():"+principal.getName());

        return "purchaseCategory/purchaseCategory-update";
    }

    @PostMapping("purchaseCategory/add")
    public String addPurchaseCategory(@Valid PurchaseCategory purchaseCategory, BindingResult result, Model model,Principal principal) {

        //system.out.println("# principal.getName():"+principal.getName());
        if (result.hasErrors() || purchaseCategory.getName().isBlank() ) {
            //system.out.println("1");
            return "purchaseCategory/purchaseCategory"; // lokalizacja html
        }
        //system.out.println("2");

        purchaseCategoryService.save(purchaseCategory,principal);
        //system.out.println("3");
        return "redirect:/arkani2/purchaseCategory"; //redirect przekierowuje na url
    }

    // additional CRUD methods
    @GetMapping("purchaseCategory")
    public String showPurchaseCategoryList(Model model,Principal principal) {
        model.addAttribute("purchaseCategory", purchaseCategoryRepo.findAll());
        model.addAttribute("username", principal.getName());
        return "purchaseCategory/purchaseCategory";
    }

    @GetMapping("purchaseCategory/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        PurchaseCategory purchaseCategory = purchaseCategoryRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid purchaseCategory Id:" + id));

        model.addAttribute("purchaseCategory", purchaseCategory);
        return "purchaseCategory/purchaseCategory-update";
    }

    @PostMapping("purchaseCategory/update/{id}")
    public String updatePurchaseCategory(@PathVariable("id") long id, @Valid PurchaseCategory purchaseCategory,
                             BindingResult result, Model model,Principal principal) {
        if (result.hasErrors()) {
            purchaseCategory.setId(id);
            return "purchaseCategory/purchaseCategory-update";
        }

        //purchaseCategory.setModBy();

        purchaseCategoryService.update(purchaseCategory,principal);
        return "redirect:/arkani2/purchaseCategory";
    }

    @GetMapping("purchaseCategory/delete/{id}")
    public String deletePurchaseCategory(@PathVariable("id") long id, Model model,Principal principal) {
        PurchaseCategory purchaseCategory = purchaseCategoryRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid purchaseCategory Id:" + id));
        //purchaseCategoryRepo.delete(purchaseCategory);

        purchaseCategoryService.delete(purchaseCategory,principal);

        return "redirect:/arkani2/purchaseCategory";
    }
}