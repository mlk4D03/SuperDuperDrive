package com.udacity.jwdnd.course1.cloudstorage.Controller;

import com.udacity.jwdnd.course1.cloudstorage.Model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controller processes Get- and Pos-Request for the endpoint /credential
 */
@Controller
@RequestMapping("/credential")
public class CredentialController {

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
     * Returns the home page with active credential tab and populates the html with data.
     *
     * @param model          Model to populate the html with data
     * @param authentication Current Authentication to get the current User
     * @return the home page with active credential tab.
     */
    @GetMapping
    String getCredentialPage(Model model, Authentication authentication) {
        model.addAttribute("notes", this.noteService.getAllNotes());
        model.addAttribute("credentials", this.credentialService.getAllCredentials());
        model.addAttribute("files", this.fileService.getAllFiles());
        model.addAttribute("iduser", this.userService.getUserId(authentication.getName()));

        model.addAttribute("activeTab", "credentials");
        model.addAttribute("encryptionService", this.encryptionService);

        return "home";
    }

    /**
     * Deletes a credential.
     *
     * @param credentialid   the id of the credential.
     * @param model          Model to populate the html with data.
     * @param authentication current Authentication to extract the current user.
     * @return the home page without the deleted credential.
     */
    @GetMapping("/delete")
    String delete(@RequestParam Integer credentialid, Model model, Authentication authentication) {
        int deletedRows = this.credentialService.deleteCredential(credentialid);
        model.addAttribute("link", "/credential");
        if (deletedRows < 0) {
            model.addAttribute("failure", true);
            return "result";
        }

        model.addAttribute("success", true);
        return "result";
    }

    /**
     * This method adds a credential.
     *
     * @param credential     the credential which should be added.
     * @param model          Model to populate the html with date.
     * @param authentication current Authentication to extract the user.
     * @return the home page with the added credential.
     */
    @PostMapping("/add")
    String add(@ModelAttribute Credential credential, Model model, Authentication authentication) {
        Integer userid = this.userService.getUserId(authentication.getName());
        model.addAttribute("link", "/credential");
        if (this.credentialService.isCredentialAlreadyAvailable(credential.getCredentialid())) {
            int updatedCredential = this.credentialService.updateCredential(credential, userid);
            if (updatedCredential < 0) {
                System.out.println("Failure!");
                model.addAttribute("failure", true);
                return "result";
            }
            model.addAttribute("success", true);
            return "result";
        } else {
            int addedRows = this.credentialService.addCredential(credential, userid);
            if (addedRows < 0) {
                model.addAttribute("failure", true);
                return "result";
            }
            model.addAttribute("success", true);
            return "result";
        }
    }
}
