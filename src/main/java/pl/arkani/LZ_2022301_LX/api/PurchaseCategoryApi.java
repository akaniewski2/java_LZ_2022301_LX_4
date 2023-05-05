package pl.arkani.LZ_2022301_LX.api;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.arkani.LZ_2022301_LX.model.PurchaseCategory;
import pl.arkani.LZ_2022301_LX.repo.PurchaseCategoryRepo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/getcategories")
@AllArgsConstructor
public class PurchaseCategoryApi {

    @Autowired
    private PurchaseCategoryRepo purchaseCategoryRepo;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getAllCategories() {
        List<PurchaseCategory> categories = purchaseCategoryRepo.findAll();

        Map<String, Object> response = new HashMap<>();
        response.put("error", false);
        response.put("message", "Request successfully completed");

        List<Map<String, Object>> categoryList = new ArrayList<>();
        for (PurchaseCategory category : categories) {
            Map<String, Object> categoryMap = new HashMap<>();
            categoryMap.put("id", category.getId());
            categoryMap.put("kategoria", category.getName());
            categoryMap.put("kolejnosc", category.getOrderr());
            categoryList.add(categoryMap);
        }

        response.put("kategorie", categoryList);

        return ResponseEntity.ok(response);
    }
}


