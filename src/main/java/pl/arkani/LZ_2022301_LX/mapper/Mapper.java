package pl.arkani.LZ_2022301_LX.mapper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import pl.arkani.LZ_2022301_LX.model.*;
import pl.arkani.LZ_2022301_LX.repo.PurchaseCategoryRepo;
import pl.arkani.LZ_2022301_LX.repo.PurchaseRepo;
import pl.arkani.LZ_2022301_LX.repo.ZakupyRepo;

import javax.naming.ldap.PagedResultsControl;

@AllArgsConstructor
@NoArgsConstructor
public class Mapper {


    private PurchaseCategoryRepo purchaseCategoryRepo;
//    private ZakupyRepo zakupyRepo;`
  //  private PurchaseRepo purchaseRepo;

    public Purchase CulinaryIngredientsToPurchase(CulinaryRecipes culinaryRecipes, CulinaryIngredients culinaryIngredients) {

        PurchaseCategory purchaseCategory = purchaseCategoryRepo.findByName("tmp");


        Purchase purchase = new Purchase();
        purchase.setName(culinaryIngredients.getName());
        purchase.setSubCategory(culinaryRecipes.getName()); //culinaryIngredients.getCulinaryRecipes().getName()
        purchase.setPurchaseCategory(purchaseCategory); //9-spożywka
        purchase.setPurchased("N");

        //System.out.println(purchase);

        return purchase;
    }

    public Zakupy PurchaseToZakupy (Purchase purchase) {


        Zakupy zakupy = new Zakupy();
        zakupy.setTowar(purchase.getName().concat(" [").concat(purchase.getSubCategory()).concat("]") );
        zakupy.setKategoria(purchase.getPurchaseCategory().getName());
        zakupy.setKupione("N");
        zakupy.setStan("0");

        System.out.println("#PurchaseToZakupy:"+zakupy);

        return zakupy;

    }




}
