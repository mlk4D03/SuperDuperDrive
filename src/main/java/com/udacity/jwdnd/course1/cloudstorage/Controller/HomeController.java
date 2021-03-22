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

    @PostMapping("/note")
    String addNote(@ModelAttribute Note note, Model model, Authentication authentication){
        String username = authentication.getName();
        if(this.noteService.isNoteAlreadyAvailable(note.getNoteid())){
            // TODO Fehlermessage ausgeben, wenn beim aktualisieren was falsch läuft
            this.noteService.updateNote(note.getNotetitle(),note.getDescription(),note.getNoteid());
        } else {
            Integer userId = this.userService.getUserId(username);
            // TODO Fehlermessage ausgeben, wenn beim Einfügen was falsch läuft
            int rowsAdded = this.noteService.createNote(note,userId);
        }
        List<Note> allNotes = this.noteService.getAllNotes();
        model.addAttribute("notes",allNotes);
        model.addAttribute("activeTab","notes");
        return "home";
    }

    @PostMapping("/deletenote")
    String deleteNote(@ModelAttribute Note note, Model model){
        int deletedRows = this.noteService.deleteNote(note.getNoteid());
        model.addAttribute("notes",this.noteService.getAllNotes());
        model.addAttribute("activeTab","notes");
        return "home";
    }

    @PostMapping("/credential")
    String addCredential(@ModelAttribute Credential credential,Model model,Authentication authentication){
        Integer userid = this.userService.getUserId(authentication.getName());
        if(this.credentialService.isCredentialAlreadyAvailable(credential.getCredentialid())){
            this.credentialService.updateCredential(credential,userid);
        } else {
            int addedRows = this.credentialService.addCredential(credential,userid);
        }

        model.addAttribute("activeTab","credentials");
        model.addAttribute("credentials",this.credentialService.getAllCredentials());
        model.addAttribute("encryptionService",this.encryptionService);
        return "home";
    }

    @PostMapping("/credentialdelete")
    String deleteCredential(@ModelAttribute Credential credential,Model model){
        // TODO pruefen wenn löschen fehlgeschlagen ist
        int deletedRows = this.credentialService.deleteCredential(credential.getCredentialid());
        model.addAttribute("credentials",this.credentialService.getAllCredentials());
        model.addAttribute("activeTab","credentials");
        model.addAttribute("encryptionService",this.encryptionService);
        return "home";
    }

    @PostMapping("/fileupload")
    String handleFileUpload(@ModelAttribute MultipartFile fileUpload, Model model, Authentication authentication) throws IOException {
        int addedRows = this.fileService.insertFile(fileUpload,this.userService.getUserId(authentication.getName()));
        model.addAttribute("activeTab","files");
        model.addAttribute("files",this.fileService.getAllFiles());
        return "home";
    }

    @GetMapping("/download")
    ResponseEntity downloadFile(@RequestParam Integer fileid){
        File downloadFile = this.fileService.getFile(fileid);

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(downloadFile.getContenttype())).
                header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + downloadFile.getFilename() +
                        "\"").body(new ByteArrayResource(downloadFile.getFiledata()));
    }

    @GetMapping("/filedelete")
    public String deleteFile(@RequestParam Integer fileid, Model model){
        int deletedRows = this.fileService.deleteFile(fileid);
        model.addAttribute("activeTab","files");
        model.addAttribute("files",this.fileService.getAllFiles());
        return "home";

    }
}
