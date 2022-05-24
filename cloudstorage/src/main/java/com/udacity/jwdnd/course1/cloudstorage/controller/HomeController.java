package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.dto.CredentialDTO;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HomeController
 *
 * @author Do Thanh
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    //=====================================================
    //                                             CONSTANT
    //                                             ========


    //=====================================================
    //                                                   DI
    //                                             ========

    /** noteService */
    private NoteService noteService;

    /** fileService */
    private FileService fileService;

    /** userService */
    private UserService userService;

    /** credentialService */
    private CredentialService credentialService;

    public HomeController(NoteService noteService, FileService fileService
                            ,UserService userService, CredentialService credentialService) {
        this.noteService = noteService;
        this.fileService = fileService;
        this.userService = userService;
        this.credentialService = credentialService;
    }

    //=====================================================
    //                                       EXECUTE METHOD
    //                                             ========

    /**
     * Screen Homepage
     *
     * @return Screen Homepage
     */
    @GetMapping()
    public String getHomePage(Model model, RedirectAttributes redirectAttrs) {

        Integer userId;
        try {
            userId = userService.getUserID();
        }
        catch (Exception e) {
            redirectAttrs.addFlashAttribute("message", UserService.NOT_EXIST_USER_MESSAGE);
            return "redirect:/login";
        }

        // Get the list files
        List<File> fileList = fileService.getListFile(userId);

        // Get the list notes
        List<Note> noteList = noteService.getListNote(userId);

        // Get the list Credentials
        List<CredentialDTO> credentialList = credentialService.getListCredential(userId);

        // Send value to Thymeleaf
        Map<String, Object> map = new HashMap<>();
        map.put("fileList", fileList);
        map.put("noteList", noteList);
        map.put("credentialList", credentialList);

        model.addAllAttributes(map);

        return "home";
    }

    //=====================================================
    //                                      INTERNAL METHOD
    //                                             ========


}
