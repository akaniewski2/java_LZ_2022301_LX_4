package pl.arkani.LZ_2022301_LX.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.arkani.LZ_2022301_LX.model.CulinaryIngredients;
import pl.arkani.LZ_2022301_LX.model.CulinaryRecipes;
import pl.arkani.LZ_2022301_LX.repo.CulinaryRecipesRepo;

import java.util.List;


@Service
@AllArgsConstructor
public class CulinaryRecipesService {

    private CulinaryRecipesRepo culinaryRecipesRepo;
    public void saveWithIngredients(CulinaryRecipes culinaryRecipes) {
        List<CulinaryIngredients> culinaryIngredients = culinaryRecipes.getCulinaryIngredients();
        for ( CulinaryIngredients c :  culinaryIngredients)  {c.setCulinaryRecipes(culinaryRecipes); }
        culinaryRecipes.setCulinaryIngredients(culinaryIngredients);
        CulinaryRecipes culinaryRecipesSaved = culinaryRecipesRepo.save(culinaryRecipes);
    }

}
