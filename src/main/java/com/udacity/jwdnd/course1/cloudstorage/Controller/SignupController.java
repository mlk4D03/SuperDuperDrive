package com.udacity.jwdnd.course1.cloudstorage.Controller;

import com.udacity.jwdnd.course1.cloudstorage.Model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Signup Controller handles the signup of an user.
 */
@Controller
@RequestMapping("/signup")
public class SignupController {

    @Autowired
    UserService userService;

    /**
     * Return the signup page.
     *
     * @return the signup page.
     */
    @GetMapping
    String getSignUpPage() {
        return "signup";
    }

    /**
     * Signs up an user.
     *
     * @param user  the user.
     * @param model Model to populate the html with data.
     * @return SignUp page with an success or error message.
     */
    @PostMapping
    String signUpUser(@ModelAttribute User user, Model model) {
        String signupError = null;
        if (!this.userService.isUsernameAvailable(user.getUsername())) {
            signupError = "Es ist ein Fehler beim SignUp aufgetreten. Probiere es bitte erneut!";
        }
        if (signupError == null) {
            int rowsAdded = this.userService.createUser(user);
            if (rowsAdded < 0) {
                signupError = "Es ist ein Fehler beim SignUp aufgetreten. Probiere es bitte erneut!";
            }
        }
        if (signupError == null) {
            model.addAttribute("signupSuccess", true);
        } else {
            model.addAttribute("signupError", signupError);
        }
        return "signup";
    }

}
