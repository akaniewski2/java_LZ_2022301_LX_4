package pl.arkani.LZ_2022301_LX.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.arkani.LZ_2022301_LX.model.Purchase;

import pl.arkani.LZ_2022301_LX.repo.PurchaseRepo;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/arkani2/")
public class PurchaseController {


    private PurchaseRepo purchaseRepo;

    @Autowired
    public PurchaseController(PurchaseRepo purchaseRepo) {
        this.purchaseRepo = purchaseRepo;
    }


    @GetMapping("purchase/add")
    public String showSignUpForm(Purchase purchase) {
        return "purchase/purchase-add";
    }

    @PostMapping("purchase/add")
    public String addPurchase(@Valid Purchase purchase, BindingResult result, Model model) {
        if (result.hasErrors() || purchase.getName().isBlank() ) {
            System.out.println("1");
            return "purchase/purchase-add"; // lokalizacja html
        }
        System.out.println("2");
        purchaseRepo.save(purchase);
        System.out.println("3");
        return "redirect:/arkani2/purchase"; //redirect przekierowuje na url
    }

    // additional CRUD methods
    @GetMapping("purchase")
    public String showPurchaseList(Model model) {
        model.addAttribute("purchase", purchaseRepo.findAll());
        return "purchase/purchase";
    }

    @GetMapping("purchase/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Purchase purchase = purchaseRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid purchase Id:" + id));

        model.addAttribute("purchase", purchase);
        return "purchase/purchase-update";
    }

    @PostMapping("purchase/update/{id}")
    public String updatePurchase(@PathVariable("id") long id, @Valid Purchase purchase,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            purchase.setId(id);
            return "purchase/purchase-update";
        }

        purchaseRepo.save(purchase);
        return "redirect:/arkani2/purchase";
    }

    @GetMapping("purchase/delete/{id}")
    public String deletePurchase(@PathVariable("id") long id, Model model) {
        Purchase purchase = purchaseRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid purchase Id:" + id));
        purchaseRepo.delete(purchase);
        return "redirect:/arkani2/purchase";
    }
}