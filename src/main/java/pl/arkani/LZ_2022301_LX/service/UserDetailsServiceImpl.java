//package pl.arkani.LZ_2022301_LX.service;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import pl.arkani.LZ_2022301_LX.repo.UserRepo;
//
//
////warstwa posrednicząca miedzy repozytorium a konfiguracja springa loadUserByUsername
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    private UserRepo userRepo;
//
//    public UserDetailsServiceImpl(UserRepo appUserRepo) {
//        this.userRepo = userRepo;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//
//         return userRepo.findByUsername(s).orElseThrow(()->new RuntimeException("Nieprawidłowy login lub hasło"));
//    }
//}