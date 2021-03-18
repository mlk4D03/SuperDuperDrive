package com.udacity.jwdnd.course1.cloudstorage.Controller;

import com.udacity.jwdnd.course1.cloudstorage.Model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    NoteService noteService;

    @Autowired
    UserService userService;

    @GetMapping
    String getHomePage(Model model){
        model.addAttribute("activeTab","files");
        model.addAttribute("notes",this.noteService.getAllNotes());
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
}
