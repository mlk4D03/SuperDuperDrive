package com.udacity.jwdnd.course1.cloudstorage.Controller;

import com.udacity.jwdnd.course1.cloudstorage.Model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/file")
public class FileController {

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
    String getFilePage(Model model,Authentication authentication){
        model.addAttribute("activeTab","files");

        model.addAttribute("notes",this.noteService.getAllNotes());
        model.addAttribute("credentials",this.credentialService.getAllCredentials());
        model.addAttribute("files",this.fileService.getAllFiles());
        model.addAttribute("encryptionService",this.encryptionService);
        model.addAttribute("iduser",this.userService.getUserId(authentication.getName()));

        return "home";
    }


    @GetMapping("/delete")
    String delete(@RequestParam Integer fileid, Model model, Authentication authentication){
        File file = this.fileService.getFile(fileid);
        Integer userid = this.userService.getUserId(authentication.getName());
        int deletedRows = this.fileService.deleteFile(fileid);
        if(deletedRows < 0){
            //model.addAttribute("fileError",true);
            model.addAttribute("failure",true);
        } else {
            model.addAttribute("success",true);
        }

        model.addAttribute("link", "/file");
        return "result";
    }

    @PostMapping("/upload")
    String handleFileUpload(@ModelAttribute MultipartFile fileUpload, Model model, org.springframework.security.core.Authentication authentication) throws IOException {
        model.addAttribute("link","/file");
        if(!fileUpload.isEmpty()){
            if(!this.fileService.isFilenameInUse(fileUpload.getOriginalFilename())){
                int addedRows = this.fileService.insertFile(fileUpload,this.userService.getUserId(authentication.getName()));
                if(addedRows < 0){
                    model.addAttribute("failure",true);
                    return "result";
                    //model.addAttribute("fileError",true);
                }
                model.addAttribute("success",true);
                return "result";
            } else {
                model.addAttribute("error",true);
                model.addAttribute("errorMessage","The filename already exists!");
                return "result";
                //model.addAttribute("fileAlreadyExists",true);
            }

        }

        model.addAttribute("error",true);
        model.addAttribute("errorMessage","You have to choose a file!");
        return "result";
    }

    @GetMapping("/download")
    ResponseEntity downloadFile(@RequestParam Integer fileid){
        File downloadFile = this.fileService.getFile(fileid);

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(downloadFile.getContenttype())).
                header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + downloadFile.getFilename() +
                        "\"").body(new ByteArrayResource(downloadFile.getFiledata()));
    }
}
