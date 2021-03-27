package com.udacity.jwdnd.course1.cloudstorage.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Login Controller to handle the Login.
 */
@Controller
public class LoginController {

    /**
     * Only returns the login page.
     *
     * @param model not uses actually.
     * @return the login page.
     */
    @GetMapping("/login")
    String getLoginPage(Model model) {
        return "login";
    }

    /**
     * Handles a login error.
     *
     * @param model Model to populate the html with a error message.
     * @return the login page with a error message.
     */
    @GetMapping("/login-error")
    String getLoginErrorMsg(Model model) {
        model.addAttribute("loginFailure", true);
        return "login";
    }
}
