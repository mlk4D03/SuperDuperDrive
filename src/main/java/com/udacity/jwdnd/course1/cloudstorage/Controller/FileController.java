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
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller for handling incoming Get- and Post-Request for the endpoint /file
 */
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

    private final long FILE_SIZE_RESTRICTION = 10000000;

    /**
     * Returns the home page with the active file tab.
     *
     * @param model          Model to populate the html with data.
     * @param authentication current Authentication to extract the user.
     * @return the home page.
     */
    @GetMapping
    String getFilePage(Model model, Authentication authentication) {
        model.addAttribute("activeTab", "files");

        model.addAttribute("notes", this.noteService.getAllNotes());
        model.addAttribute("credentials", this.credentialService.getAllCredentials());
        model.addAttribute("files", this.fileService.getAllFiles());
        model.addAttribute("encryptionService", this.encryptionService);
        model.addAttribute("iduser", this.userService.getUserId(authentication.getName()));

        return "home";
    }


    /**
     * Deletes a file from the file table.
     *
     * @param fileid         the id of the file.
     * @param model          Model to populate the html with data.
     * @param authentication current Authentication to extract he user.
     * @return the home page without the deleted file.
     */
    @GetMapping("/delete")
    String delete(@RequestParam Integer fileid, Model model, Authentication authentication) {
        File file = this.fileService.getFile(fileid);
        Integer userid = this.userService.getUserId(authentication.getName());
        int deletedRows = this.fileService.deleteFile(fileid);
        if (deletedRows < 0) {
            //model.addAttribute("fileError",true);
            model.addAttribute("failure", true);
        } else {
            model.addAttribute("success", true);
        }

        model.addAttribute("link", "/file");
        return "result";
    }

    /**
     * Uploads a file into the database.
     *
     * @param fileUpload     the file.
     * @param model          Model to populate the html with data.
     * @param authentication current Authentication to extract the current user.
     * @return the home page with the added file in the file table.
     * @throws IOException
     */
    @PostMapping("/upload")
    String handleFileUpload(@ModelAttribute MultipartFile fileUpload, Model model, Authentication authentication) throws IOException {
        model.addAttribute("link", "/file");
        if (!fileUpload.isEmpty()) {
            if (!(fileUpload.getSize() > FILE_SIZE_RESTRICTION)) {
                if (!this.fileService.isFilenameInUse(fileUpload.getOriginalFilename())) {
                    int addedRows = this.fileService.insertFile(fileUpload, this.userService.getUserId(authentication.getName()));
                    if (addedRows < 0) {
                        model.addAttribute("failure", true);
                        return "result";
                    }
                    model.addAttribute("success", true);
                    return "result";
                } else {
                    model.addAttribute("error", true);
                    model.addAttribute("errorMessage", "The filename already exists!");
                    return "result";
                }
            } else {
                model.addAttribute("error", true);
                model.addAttribute("errorMessage", "The size of the file is too large. Please upload a file not bigger as 10MB.");
                return "result";
            }


        }

        model.addAttribute("error", true);
        model.addAttribute("errorMessage", "You have to choose a file!");
        return "result";
    }

    /**
     * Downloads a file from the file table.
     *
     * @param fileid the id of the file.
     * @return the file.
     */
    @GetMapping("/download")
    ResponseEntity downloadFile(@RequestParam Integer fileid) {
        File downloadFile = this.fileService.getFile(fileid);

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(downloadFile.getContenttype())).
                header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + downloadFile.getFilename() +
                        "\"").body(new ByteArrayResource(downloadFile.getFiledata()));
    }
}
