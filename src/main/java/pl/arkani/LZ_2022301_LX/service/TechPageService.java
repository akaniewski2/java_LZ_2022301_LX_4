package pl.arkani.LZ_2022301_LX.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import pl.arkani.LZ_2022301_LX.model.TechPage;
import pl.arkani.LZ_2022301_LX.model.TechPageTmp;
import pl.arkani.LZ_2022301_LX.model.User;
import pl.arkani.LZ_2022301_LX.repo.TechPageRepo;
import pl.arkani.LZ_2022301_LX.repo.UserRepo;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TechPageService {

//    private TechPageRepo techPageRepo;
//    private TechPage techPage;
//    private UserRepo userRepo;
//
//
//    @Autowired
//    public TechPageService(TechPageRepo techPageRepo, TechPage techPage, UserRepo userRepo) {
//        this.techPageRepo = techPageRepo;
//        this.techPage = techPage;
//        this.userRepo = userRepo;
//    }

    public String checkPrivilage(String httpMethod,String pageName, Model model, Principal principal,
                                 TechPageRepo techPageRepo, TechPage techPage, UserRepo userRepo) {
        User user = userRepo.findByUsername(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found in DB"));
        String userRole = user.getRole();
        //  String httpMethod = "GET";
        List<TechPage> techPageList = techPageRepo.findByMethodAndRole(httpMethod.toUpperCase(), userRole);
        //        List<TechPage> techPageList2 = techPageList.stream().filter(s -> s.getRoles().equals(userRole)).collect(Collectors.toList());
        List<TechPageTmp> techPageList2 = new ArrayList<>();

        for (TechPage s : techPageList) {

            techPageList2.add(new TechPageTmp(s.getName(), s.getButton(), s.getHeader(), s.getUrl()));
        }

        // Set<TechPage> techPageList2 = new HashSet<TechPage>();
        // techPageList2.addAll(techPageList);

        Optional<TechPage> techpagePrivilege = techPageRepo.findByMethodAndNameAndRole(httpMethod.toUpperCase(), pageName, userRole);
        if (techpagePrivilege.isEmpty()) {
            return null;
        }
//        System.out.println("# techPage:" + techPage);
//        System.out.println("# techPageList2:" + techPageList2);
//        techPageList.forEach(System.out::println);

        // System.out.println("#techpageRole: "+ techpagePrivilege);
        model.addAttribute("thisTechPage", techPage);
        model.addAttribute("techPageList", techPageList2);//przekazuje tylko te definicje stron , do których user ma uprawnienia
        return userRole; //todo:  tu sie zastanowić czy nie zwracac techpagePrivilege - tak jak to jest w usercontroller
    }



}
