package pl.arkani.LZ_2022301_LX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.arkani.LZ_2022301_LX.model.Purchase;
import pl.arkani.LZ_2022301_LX.model.Role;
import pl.arkani.LZ_2022301_LX.model.TechPage;
import pl.arkani.LZ_2022301_LX.model.User;
import pl.arkani.LZ_2022301_LX.repo.PurchaseRepo;
import pl.arkani.LZ_2022301_LX.repo.TvChannelRepo;
import pl.arkani.LZ_2022301_LX.repo.UserRepo;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

@Component
public class Start {

    private TvChannelRepo tvChannelRepo;
    private UserRepo userRepo;

    private PasswordEncoder passwordEncoder;
    private final PurchaseRepo purchaseRepo;

    @Autowired
    public Start(TvChannelRepo tvChannelRepo, UserRepo userRepo, PasswordEncoder passwordEncoder,
                 PurchaseRepo purchaseRepo){
        this.tvChannelRepo=tvChannelRepo;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.purchaseRepo = purchaseRepo;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runExample() throws UnknownHostException {


        //-- OPTIONAL -----------------------------------------------------------------------------

//        Optional.of(techpagePrivilege.get().getName()); // zakłada ze optional nigdy nie bedzie nullem
//
//        System.out.println("-- # OPTIONAL ---");
//        if (techpagePrivilege.isPresent() ) {
//
//            System.out.println(techpagePrivilege.get().getName());
//        }


        //system.out.println(InetAddress.getLocalHost().getHostName());

       // Purchase purchase = new Purchase();


//      --zapis
//        TvChannel tvChannel = new TvChannel();
//        tvChannel.setName("TVP 1");
//        tvChannel.setActive(0);
//        tvChannel.setCode("1153139325,1153122495");
//        tvChannel.setTag("ogolny,film");
//        tvChannel.setPrior(0);
//        tvChannelRepo.save(tvChannel);
//      --odczyt

//        tvChannel = new TvChannel(0,"TVP 2",null,0,"1153155135,1153122495","ogolny,film",0);
//        tvChannelRepo.save(tvChannel);
//
//        Iterable<TvChannel> all = tvChannelRepo.findAll();
//        all.forEach(System.out::println);
//
//        all = tvChannelRepo.findByName("TVP1");
//        all.forEach(System.out::println);

//        if (!userRepo.findByUsername("admin").isPresent()   ) {
//
//            User user = new User();
//            user.setUsername("admin");
//            user.setPassword(passwordEncoder.encode("a"));
//            user.setRole("ROLE_ADMIN");
//            user.setRoles("ROLE_ADMIN");
//            user.setAuthority("ROLE_ADMIN");
//            user.setEnabled(true);
//
//            User user2 = new User();
//            user2.setUsername("user");
//            user2.setPassword(passwordEncoder.encode("u"));
//            user2.setRole("ROLE_ADMIN");
//            user2.setRoles("ROLE_USER");
//            user2.setAuthority("ROLE_USER");
//            user2.setEnabled(true);
//
//
//            User user3 = new User();
//            user3.setUsername("guest");
//            user3.setPassword(passwordEncoder.encode("g"));
//            user3.setRole("ROLE_ADMIN");
//            user3.setRoles("ROLE_GUEST");
//            user3.setAuthority("ROLE_GUEST");
//            user3.setEnabled(true);
//
//            userRepo.delete(user);
//            userRepo.delete(user2);
//            userRepo.delete(user3);
//            userRepo.save(user);
//            userRepo.save(user2);
//            userRepo.save(user3);
//        }


    }

}
