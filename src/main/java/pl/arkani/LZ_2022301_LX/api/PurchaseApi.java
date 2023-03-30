package pl.arkani.LZ_2022301_LX.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.arkani.LZ_2022301_LX.model.Purchase;
import pl.arkani.LZ_2022301_LX.service.PurchaseService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/purchase",produces = MediaType.APPLICATION_JSON_VALUE)
public class PurchaseApi {


    private final PurchaseService purchaseService;

    @Autowired
    public PurchaseApi(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping
    public List<Purchase> get() {
        return null;
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
