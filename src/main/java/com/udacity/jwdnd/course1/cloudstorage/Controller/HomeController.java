package com.udacity.jwdnd.course1.cloudstorage.Controller;

import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * Home Controller handles Requests for the endpoint /home
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    NoteService noteService;

    @Autowired
    UserService userService;

    @Autowired
    CredentialService credentialService;

    @Autowired
    EncryptionService encryptionService;

    @Autowired
    FileService fileService;

    /**
     * Returns the home page with all data.
     *
     * @param model          Model to populate the html with data.
     * @param authentication current Authentication to extract current user.
     * @return the home page.
     */
    @GetMapping
    String getHomePage(Model model, Authentication authentication) {
        model.addAttribute("activeTab", "files");
        model.addAttribute("notes", this.noteService.getAllNotes());
        model.addAttribute("credentials", this.credentialService.getAllCredentials());
        model.addAttribute("files", this.fileService.getAllFiles());
        model.addAttribute("encryptionService", this.encryptionService);
        model.addAttribute("iduser", this.userService.getUserId(authentication.getName()));
        return "home";
    }
}
