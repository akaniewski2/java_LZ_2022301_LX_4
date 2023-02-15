package pl.arkani.LZ_2022301_LX;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.arkani.LZ_2022301_LX.model.User;
import pl.arkani.LZ_2022301_LX.repo.TvChannelRepo;
import pl.arkani.LZ_2022301_LX.repo.UserRepo;

@Component
public class Start {

    private TvChannelRepo tvChannelRepo;
    private UserRepo userRepo;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public Start(TvChannelRepo tvChannelRepo, UserRepo userRepo, PasswordEncoder passwordEncoder){
        this.tvChannelRepo=tvChannelRepo;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runExample() {

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

        if (!userRepo.findByUsername("admin").isPresent()   ) {

            User user = new User();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("a"));
            user.setRole("ADMIN");
            user.setRoles("ADMIN");
            user.setEnabled(true);

            User user2 = new User();
            user2.setUsername("user");
            user2.setPassword(passwordEncoder.encode("u"));
            user2.setRole("USER");
            user2.setRoles("USER");
            user2.setEnabled(true);


            User user3 = new User();
            user3.setUsername("guest");
            user3.setPassword(passwordEncoder.encode("g"));
            user3.setRole("GUEST");
            user3.setRoles("GUEST");
            user3.setEnabled(true);

            userRepo.delete(user);
            userRepo.delete(user2);
            userRepo.delete(user3);
            userRepo.save(user);
            userRepo.save(user2);
            userRepo.save(user3);
        }

    }

}
