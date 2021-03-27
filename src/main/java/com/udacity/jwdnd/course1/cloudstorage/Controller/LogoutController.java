package com.udacity.jwdnd.course1.cloudstorage.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Logout Controller handles the logout from a user.
 */
@Controller
@RequestMapping("/logout")
public class LogoutController {

    /**
     * Returns the login page with a logout success message.
     *
     * @param model Model to populate the login page with a success message.
     * @return the login page.
     */
    @GetMapping
    String showLoginWithLogoutMessage(Model model) {
        model.addAttribute("logoutSuccess", true);
        return "login";
    }
}
