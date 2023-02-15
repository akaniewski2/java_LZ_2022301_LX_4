package pl.arkani.LZ_2022301_LX.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ErrorControllerCustom implements org.springframework.boot.web.servlet.error.ErrorController {


  @RequestMapping("/error_")
  @ResponseBody
  String error(HttpServletRequest request) {
     return "<h1>Error occurred</h1>";
  }

  //@Override
  public String getErrorPath() {
    return "/error";
  }
}