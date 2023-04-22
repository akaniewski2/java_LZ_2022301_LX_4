package pl.arkani.LZ_2022301_LX.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.arkani.LZ_2022301_LX.model.Purchase;
import pl.arkani.LZ_2022301_LX.repo.PurchaseRepo;
import pl.arkani.LZ_2022301_LX.service.PurchaseService;

import java.util.List;

@RestController
//@RequestMapping(path = "/api/purchase",produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping(value ="/api/purchase/")
public class PurchaseApi {


    private final PurchaseService purchaseService;
    private final PurchaseRepo purchaseRepo;

    @Autowired
    public PurchaseApi(PurchaseService purchaseService, PurchaseRepo purchaseRepo) {
        this.purchaseService = purchaseService;
        this.purchaseRepo = purchaseRepo;
    }

    @GetMapping("getpurchase")
    public List<Purchase> get() {
        return purchaseRepo.findAll();
    }

    @GetMapping("/{id}")
    public  Purchase  get(@PathVariable long id) {
        return null;
    }
    @Transactional
    @PutMapping("/{id}")
    public  void  put(@PathVariable long id,@RequestBody  Purchase purchase) {

    }

    @Transactional
    @DeleteMapping("/{id}")
    public  void  delete(@PathVariable long id) {

    }
}
