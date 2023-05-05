package pl.arkani.LZ_2022301_LX.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.arkani.LZ_2022301_LX.model.PurchaseCategory;
import pl.arkani.LZ_2022301_LX.service.PurchaseCategoryService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/purchasecategory",produces = MediaType.APPLICATION_JSON_VALUE)
public class PurchaseCategoryApi_ {


    private  PurchaseCategoryService purchaseCategoryService;

    @Autowired
    public PurchaseCategoryApi_(PurchaseCategoryService purchaseCategoryService) {
        this.purchaseCategoryService = purchaseCategoryService;
    }

    @GetMapping
    public List<PurchaseCategory> get() {
        return null;
    }

    @GetMapping("/{id}")
    public  PurchaseCategory  get(@PathVariable long id) {
        return null;
    }
    @Transactional
    @PutMapping("/{id}")
    public  void  put(@PathVariable long id,@RequestBody  PurchaseCategory purchaseCategory) {

    }

    @Transactional
    @DeleteMapping("/{id}")
    public  void  delete(@PathVariable long id) {

    }


}
