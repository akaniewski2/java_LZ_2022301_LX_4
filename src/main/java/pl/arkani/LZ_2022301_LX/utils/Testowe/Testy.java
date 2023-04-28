package pl.arkani.LZ_2022301_LX.utils.Testowe;

import lombok.AllArgsConstructor;
import org.junit.Test;
import pl.arkani.LZ_2022301_LX.model.CulinaryIngredients;
import pl.arkani.LZ_2022301_LX.model.CulinaryRecipes;
import pl.arkani.LZ_2022301_LX.model.Movie;
import pl.arkani.LZ_2022301_LX.repo.CulinaryRecipesRepo;
import pl.arkani.LZ_2022301_LX.utils.Functions;

import java.util.Collections;
import java.util.List;



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


    public static void main(String[] args) {
        Movie data; // or List<SomeClass> data; etc.
    //    List<String> fieldNames = Functions.getFieldNamesForClass(data..get(0).getClass())   ;
    }



}
