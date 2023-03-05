package pl.arkani.LZ_2022301_LX.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.arkani.LZ_2022301_LX.model.Purchase;
import pl.arkani.LZ_2022301_LX.model.User;
import pl.arkani.LZ_2022301_LX.repo.PurchaseRepo;
import pl.arkani.LZ_2022301_LX.repo.UserRepo;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    private PurchaseRepo purchaseRepo;

    private UserRepo userRepo;
    private UserDetailsServiceImpl userDetailsService;




    public PurchaseService(PurchaseRepo purchaseRepo, UserRepo userRepo, UserDetailsServiceImpl userDetailsService) {
        this.purchaseRepo = purchaseRepo;
        this.userRepo = userRepo;
        this.userDetailsService = userDetailsService;
    }


    public List<Purchase> findAllWithLimit(int limit) {
        Page<Purchase> page = purchaseRepo.findAll(Pageable.ofSize(limit));
        return page.getContent();
    }


    public void save (Purchase purchase, Principal principal) {

//        //system.out.println("#principal2:"+principal2.getName());
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        if (principal instanceof UserDetails) {
//            String username = ((UserDetails)principal).getUsername();
//        } else {
//            String username = principal.toString();
//        }

        Optional<User> user = userRepo.findByUsername(principal.getName());

        purchase.setAddDt();
        purchase.setPurchased("N");

        purchase.setAddBy(principal.getName());
        purchase.setLastModBy(principal.getName());
        purchase.setCategory(purchase.getPurchaseCategory().getName());
        purchase.setUser(user.orElseThrow(() -> new IllegalArgumentException("User doesn't exist")));
    //    purchase.setPurchaseCategory(purchase.getPurchaseCategory());
        purchaseRepo.save(purchase);

    }

    public void update (Purchase purchase,Principal principal) {

//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        if (principal instanceof UserDetails) {
//            String username = ((UserDetails)principal).getUsername();
//        } else {
//            String username = principal.toString();
//        }


        Optional<User> user = userRepo.findByUsername(principal.getName());
        Optional<Purchase> purchaseHst = purchaseRepo.findById(purchase.getId());


        purchase.setAddDt(purchase.getAddDt());
        purchase.setModDt();
    //    purchase.setAddBy(purchaseHst.get().getAddBy()); // jesli jakiegos pola nie ma na formularzu edycji w metodzie PUT to albo trzeba go dodać na formularzu,albo jak tu przypisac mu ponownie wartosc - inaczej bedzie null
        purchase.setModBy(principal.getName());
        purchase.setLastModBy(principal.getName());
        purchase.setCategory(purchase.getPurchaseCategory().getName());
    //    purchase.setPurchaseCategory(purchaseHst.get().getPurchaseCategory());
        //purchase.setPurchaseCategory(purchaseHst.get().getPurchaseCategory());
      //  purchase.setCategory(purchaseHst.get().getPurchaseCategory().getName());
        purchase.setUser(user.orElseThrow(() -> new IllegalArgumentException("User doesn't exist")));
        purchaseRepo.save(purchase);


    }


    public void delete(Purchase purchase, Principal principal) {

        Optional<User> user = userRepo.findByUsername(principal.getName());

        purchase.setPurchased("Y");
        purchase.setAddDt(purchase.getAddDt());
        purchase.setModDt();
        purchase.setModBy(principal.getName());
        purchase.setLastModBy(principal.getName());
        purchase.setUser(user.orElseThrow(() -> new IllegalArgumentException("User doesn't exist")));
        purchaseRepo.save(purchase);

    }
}
