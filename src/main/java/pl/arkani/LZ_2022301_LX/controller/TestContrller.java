package pl.arkani.LZ_2022301_LX.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class TestContrller {


    @GetMapping("/admin")
    public String admin1 () {
        return "admin1";
    }



    @GetMapping("/admin/2")
    public String admin2 () {
        return "admin2";
    }


    @GetMapping("/user")
    public String user1 () {
        return "user1";
    }



    @GetMapping("/user/2")
    public String user2 () {
        return "user2";
    }



}


