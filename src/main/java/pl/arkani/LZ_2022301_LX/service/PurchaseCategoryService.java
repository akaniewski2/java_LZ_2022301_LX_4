package pl.arkani.LZ_2022301_LX.service;

import org.springframework.stereotype.Service;
import pl.arkani.LZ_2022301_LX.model.PurchaseCategory;
import pl.arkani.LZ_2022301_LX.model.User;
import pl.arkani.LZ_2022301_LX.repo.PurchaseCategoryRepo;
import pl.arkani.LZ_2022301_LX.repo.PurchaseRepo;
import pl.arkani.LZ_2022301_LX.repo.UserRepo;

import java.security.Principal;
import java.util.Optional;

@Service
public class PurchaseCategoryService {

    private PurchaseCategoryRepo purchaseCategoryRepo;

    private UserRepo userRepo;
    private UserDetailsServiceImpl userDetailsService;
    private final PurchaseRepo purchaseRepo;


    public PurchaseCategoryService(PurchaseCategoryRepo purchaseCategoryRepo, UserRepo userRepo, UserDetailsServiceImpl userDetailsService,
                                   PurchaseRepo purchaseRepo) {
        this.purchaseCategoryRepo = purchaseCategoryRepo;
        this.userRepo = userRepo;
        this.userDetailsService = userDetailsService;
        this.purchaseRepo = purchaseRepo;
    }





    public void save (PurchaseCategory purchaseCategory, Principal principal) {

//        //system.out.println("#principal2:"+principal2.getName());
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        if (principal instanceof UserDetails) {
//            String username = ((UserDetails)principal).getUsername();
//        } else {
//            String username = principal.toString();
//        }

//        Optional<User> user = userRepo.findByUsername(principal.getName());

//        purchaseCategory.setAddDt();
//        purchaseCategory.setPurchaseCategoryd("N");
//        purchaseCategory.setAddBy(principal.getName());
//        purchaseCategory.setLastModBy(principal.getName());
//        purchaseCategory.setUser(user.orElseThrow(() -> new IllegalArgumentException("User doesn't exist")));

        purchaseCategoryRepo.save(purchaseCategory);

    }

    public void update (PurchaseCategory purchaseCategory,Principal principal) {

//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        if (principal instanceof UserDetails) {
//            String username = ((UserDetails)principal).getUsername();
//        } else {
//            String username = principal.toString();
//        }


//        Optional<User> user = userRepo.findByUsername(principal.getName());
//        Optional<PurchaseCategory> purchaseCategoryHst = purchaseCategoryRepo.findById(purchaseCategory.getId());


//        purchaseCategory.setAddDt(purchaseCategory.getAddDt());
//        purchaseCategory.setModDt();
//        purchaseCategory.setAddBy(purchaseCategoryHst.get().getAddBy()); // jesli jakiegos pola nie ma na formularzu edycji w metodzie PUT to albo trzeba go dodać na formularzu,albo jak tu przypisac mu ponownie wartosc - inaczej bedzie null
//        purchaseCategory.setModBy(principal.getName());
//        purchaseCategory.setLastModBy(principal.getName());
//        purchaseCategory.setUser(user.orElseThrow(() -> new IllegalArgumentException("User doesn't exist")));

        purchaseCategoryRepo.save(purchaseCategory);


    }


    public void delete(PurchaseCategory purchaseCategory, Principal principal) {

//        Optional<User> user = userRepo.findByUsername(principal.getName());

//        purchaseCategory.setPurchaseCategoryd("Y");
//        purchaseCategory.setAddDt(purchaseCategory.getAddDt());
//        purchaseCategory.setModDt();
//        purchaseCategory.setModBy(principal.getName());
//        purchaseCategory.setLastModBy(principal.getName());
//        purchaseCategory.setUser(user.orElseThrow(() -> new IllegalArgumentException("User doesn't exist")));


          purchaseCategory.setActiveRec(0);
          purchaseCategoryRepo.save(purchaseCategory);
         //purchaseCategoryRepo.updateActiveById(purchaseCategory.getId());


    }
}
