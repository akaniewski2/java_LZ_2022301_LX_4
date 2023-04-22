package pl.arkani.LZ_2022301_LX.utils.Testowe;

import lombok.AllArgsConstructor;
import org.junit.Test;
import pl.arkani.LZ_2022301_LX.model.CulinaryIngredients;
import pl.arkani.LZ_2022301_LX.model.CulinaryRecipes;
import pl.arkani.LZ_2022301_LX.repo.CulinaryRecipesRepo;

import java.util.Collections;

@AllArgsConstructor
public class Testy {

    CulinaryRecipesRepo culinaryRecipesRepo ;

    public Testy() {

    }


   public void saveClass () {

        CulinaryRecipes culinaryRecipes = new CulinaryRecipes();
        CulinaryIngredients culinaryIngredients = new CulinaryIngredients();

        culinaryRecipes.setName("test20");
        culinaryIngredients.setName("a20");

        culinaryRecipes.setCulinaryIngredients(Collections.singletonList(culinaryIngredients));

        culinaryRecipesRepo.save(culinaryRecipes);
    }





}
