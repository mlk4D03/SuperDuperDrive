package com.udacity.jwdnd.course1.cloudstorage.Controller;

import com.udacity.jwdnd.course1.cloudstorage.Model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/delete")
    String delete(@RequestParam Integer credentialid, Model model, Authentication authentication){
            int deletedRows = this.credentialService.deleteCredential(credentialid);
            if(deletedRows < 0){
                model.addAttribute("credentialError",true);
            }

        model.addAttribute("notes",this.noteService.getAllNotes());
        model.addAttribute("credentials",this.credentialService.getAllCredentials());
        model.addAttribute("files",this.fileService.getAllFiles());
        model.addAttribute("iduser",this.userService.getUserId(authentication.getName()));

        model.addAttribute("activeTab","credentials");
        model.addAttribute("encryptionService",this.encryptionService);

        return "home";
    }

    @PostMapping("/add")
    String add(@ModelAttribute Credential credential, Model model, Authentication authentication){
        Integer userid = this.userService.getUserId(authentication.getName());
        if(this.credentialService.isCredentialAlreadyAvailable(credential.getCredentialid())){
                int updatedCredential = this.credentialService.updateCredential(credential,userid);
                if(updatedCredential < 0){
                    model.addAttribute("credentialError",true);
                }
        } else {
            int addedRows = this.credentialService.addCredential(credential,userid);
            if(addedRows < 0){
                model.addAttribute("credentialError",true);
            }
        }

        model.addAttribute("activeTab","credentials");

        model.addAttribute("notes",this.noteService.getAllNotes());
        model.addAttribute("credentials",this.credentialService.getAllCredentials());
        model.addAttribute("files",this.fileService.getAllFiles());
        model.addAttribute("encryptionService",this.encryptionService);
        model.addAttribute("iduser",this.userService.getUserId(authentication.getName()));

        return "home";
    }
}
