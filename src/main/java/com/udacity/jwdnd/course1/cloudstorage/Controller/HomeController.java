package com.udacity.jwdnd.course1.cloudstorage.Controller;

import com.udacity.jwdnd.course1.cloudstorage.Model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.Model.File;
import com.udacity.jwdnd.course1.cloudstorage.Model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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

    @GetMapping
    String getHomePage(Model model,Authentication authentication){
        model.addAttribute("activeTab","files");
        model.addAttribute("notes",this.noteService.getAllNotes());
        model.addAttribute("credentials",this.credentialService.getAllCredentials());
        model.addAttribute("files",this.fileService.getAllFiles());
        model.addAttribute("encryptionService",this.encryptionService);
        model.addAttribute("iduser",this.userService.getUserId(authentication.getName()));
        return "home";
    }
}
