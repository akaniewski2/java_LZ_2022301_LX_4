package pl.arkani.LZ_2022301_LX.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/error")
public class ErrorControllerCustom implements org.springframework.boot.web.servlet.error.ErrorController {


//  @RequestMapping("/error")
//  @ResponseBody
//  String error(HttpServletRequest request) {
//     return "<h1>Error occurred</h1>";
//  }

  //@Override
  @GetMapping
  public String getErrorPath() {
    return "_public/error";
  }
}