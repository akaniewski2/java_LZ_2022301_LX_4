package pl.arkani.LZ_2022301_LX.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.arkani.LZ_2022301_LX.model.Purchase;
import pl.arkani.LZ_2022301_LX.model.PurchaseCategory;
import pl.arkani.LZ_2022301_LX.repo.PurchaseCategoryRepo;
import pl.arkani.LZ_2022301_LX.repo.PurchaseRepo;
import pl.arkani.LZ_2022301_LX.service.PurchaseService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/test/categories")
public class PurchaseCategoryController_GPT {

    @Autowired
    private PurchaseCategoryRepo purchaseCategoryRepository;

    @GetMapping
    public List<PurchaseCategory> getAllPurchaseCategories() {
        return purchaseCategoryRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseCategory> getPurchaseCategoryById(@PathVariable Long id) {
        Optional<PurchaseCategory> category = purchaseCategoryRepository.findById(id);
        if (category.isPresent()) {
            return ResponseEntity.ok(category.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PurchaseCategory> createPurchaseCategory(@Validated(PurchaseCategory.DataUpdateValidation.class) @RequestBody PurchaseCategory category) {
        PurchaseCategory savedCategory = purchaseCategoryRepository.save(category);
        return ResponseEntity.created(URI.create("/categories/" + savedCategory.getId())).body(savedCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PurchaseCategory> updatePurchaseCategory(@PathVariable Long id, @Validated(PurchaseCategory.DataUpdateValidation.class) @RequestBody PurchaseCategory category) {
        Optional<PurchaseCategory> existingCategory = purchaseCategoryRepository.findById(id);
        if (existingCategory.isPresent()) {
            PurchaseCategory savedCategory = purchaseCategoryRepository.save(category);
            return ResponseEntity.ok(savedCategory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePurchaseCategory(@PathVariable Long id) {
        Optional<PurchaseCategory> category = purchaseCategoryRepository.findById(id);
        if (category.isPresent()) {
            purchaseCategoryRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}