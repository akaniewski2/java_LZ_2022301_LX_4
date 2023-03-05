package pl.arkani.LZ_2022301_LX.service;



import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.arkani.LZ_2022301_LX.model.Role;
import pl.arkani.LZ_2022301_LX.model.Token;
import pl.arkani.LZ_2022301_LX.model.User;
import pl.arkani.LZ_2022301_LX.repo.TokenRepo;
import pl.arkani.LZ_2022301_LX.repo.UserRepo;


import java.util.UUID;

@Service
public class UserService {

    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;

    //testowo wstrzykuje przez zmienna
    private TokenRepo tokenRepo;
    private MailService mailService;

    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder, TokenRepo tokenRepo, MailService mailService) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepo = tokenRepo;
        this.mailService = mailService;
    }

    public void addUser(User user)  {
      //  if (appUser.getPassword().equals(appUser.getPassword2())
        //
        //  ) {
        if (user.getCode().equals("#k")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setPassword2(passwordEncoder.encode(user.getPassword2()));

            user.setRole("ROLE_GUEST");
            user.setAuthority("ROLE_GUEST");


            if (!userRepo.findByUsername(user.getUsername()).isPresent() )  {
                userRepo.save(user);
                sendToken(user);
              //  //system.out.println(user);
            } else {
                //system.out.println("Podany login już istnieje");

            }


        } else {
           //system.out.println("Hasła nie zgadzają się 1");

       }

    }

    private void sendToken(User user) {
        String tokenValue = UUID.randomUUID().toString();
        Token token = new Token();
        token.setValue(tokenValue);
        token.setAppUser(user);
        tokenRepo.save(token);

        String url ="http://localhost:8080/token?value="+tokenValue;
        try {
            mailService.sendMail(user.getEmail(),"Potwierdź rejestracje",url,false);
        } catch (jakarta.mail.MessagingException e) {
            throw new RuntimeException(e);
        }

    }

}
