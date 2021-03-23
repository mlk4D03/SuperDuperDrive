package com.udacity.jwdnd.course1.cloudstorage.Controller;

import com.udacity.jwdnd.course1.cloudstorage.Model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/note")
public class NoteController {

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
    String getNotePage(Model model, Authentication authentication){
        model.addAttribute("activeTab","notes");
        model.addAttribute("encryptionService",this.encryptionService);

        model.addAttribute("notes",this.noteService.getAllNotes());
        model.addAttribute("credentials",this.credentialService.getAllCredentials());
        model.addAttribute("files",this.fileService.getAllFiles());
        model.addAttribute("iduser",this.userService.getUserId(authentication.getName()));

        return "home";
    }

    @GetMapping("/delete")
    String delete(@RequestParam Integer noteid, Model model, Authentication authentication){
        model.addAttribute("link","/note");
        int deletedRows = this.noteService.deleteNote(noteid);
        if(deletedRows < 0){
            model.addAttribute("failure",true);
            return "result";
        }
        model.addAttribute("success",true);
        return "result";
    }

    @PostMapping("/add")
    String add(@ModelAttribute Note note, Model model, Authentication authentication){
        String username = authentication.getName();
        model.addAttribute("link","/note");
        if(this.noteService.isNoteAlreadyAvailable(note.getNoteid())){
            int updatedNote = this.noteService.updateNote(note.getNotetitle(),note.getDescription(),note.getNoteid());
            if(updatedNote < 0){
                model.addAttribute("failure",true);
                return "result";
            }
            model.addAttribute("success",true);
            return "result";
        } else {
            Integer userId = this.userService.getUserId(username);
            int rowsAdded = this.noteService.createNote(note,userId);
            if(rowsAdded < 0){
                model.addAttribute("failure",true);
                return "result";
            }
            model.addAttribute("success",true);
            return "result";
        }
    }

}
