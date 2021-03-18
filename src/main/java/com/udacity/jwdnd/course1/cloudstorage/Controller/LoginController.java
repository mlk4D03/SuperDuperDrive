package com.udacity.jwdnd.course1.cloudstorage.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    String getLoginPage(Model model){
        return "login";
    }

    @GetMapping("/login-error")
    String getLoginErrorMsg(Model model){
        model.addAttribute("loginFailure",true);
        return "login";
    }
}
