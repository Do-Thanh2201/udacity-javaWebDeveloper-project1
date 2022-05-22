package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteDTO;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * NoteController
 *
 * @author Do Thanh
 */
@Controller
@RequestMapping("/note")
public class NoteController {


    //=====================================================
    //                                             CONSTANT
    //                                             ========

    /** Empty Uploaded Note message */
    private static final String EMPTY_NOTE_MESSAGE             = "Please fill a note to save.";

    /** Upload file successfully message */
    private static final String SAVE_NOTE_SUCCESS_MESSAGE    = "You successfully save the note!";

    /** Delete Note successfully message */
    private static final String DELETE_NOTE_SUCCESS_MESSAGE    = "You successfully delete the note!";

    /** Save note error message */
    private static final String SAVE_NOTE_ERROR_MESSAGE      = "An error occurred while saving the file!";

    /** Upload file error message */
    private static final String DELETE_NOTE_ERROR_MESSAGE      = "An error occurred while deleting the note!";


    //=====================================================
    //                                                   DI
    //                                             ========

    /** noteService*/
    private NoteService noteService;

    /** userService*/
    private UserService userService;

    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    //=====================================================
    //                                       EXECUTE METHOD
    //                                             ========

    /**
     * Screen view the list Note
     *
     * @return Screen Homepage
     */
    @GetMapping()
    public String getNote() {
        return "redirect:/home";
    }

    /**
     * Screen Add new the Note
     *
     * @return Screen Homepage
     */
    @PostMapping()
    public String addNote(@ModelAttribute("note") NoteDTO noteDTO, RedirectAttributes redirectAttrs) {

        // Check if file is empty
        if (noteDTO == null) {

            redirectAttrs.addFlashAttribute("message", EMPTY_NOTE_MESSAGE);
            return "redirect:/home";
        }

        Note note = noteService.setToNote(noteDTO.getNoteTitle(), noteDTO.getNoteDescription(), userService.getUserID());

        if (!noteService.insert(note)) {

            // return error response
            redirectAttrs.addFlashAttribute("message", SAVE_NOTE_ERROR_MESSAGE);
            return "redirect:/home";
        };

        redirectAttrs.addFlashAttribute("message", SAVE_NOTE_SUCCESS_MESSAGE);

        return "redirect:/home";
    }

    /**
     * Screen Edit the Note
     *
     * @return Screen Homepage
     */
    @PutMapping()
    public String editNote() {
        return "redirect:/home";
    }

    /**
     * Screen delete the Note
     *
     * @return Screen Homepage
     */
    @GetMapping ("/delete")
    public String deleteNote(@RequestParam("noteId") int noteId, RedirectAttributes redirectAttrs) {

        if (!noteService.delete(noteId)) {

            // return error response
            redirectAttrs.addFlashAttribute("message", DELETE_NOTE_ERROR_MESSAGE);
            return "redirect:/home";
        };

        // return success response
        redirectAttrs.addFlashAttribute("message", DELETE_NOTE_SUCCESS_MESSAGE);
        return "redirect:/home";
    }
    //=====================================================
    //                                      INTERNAL METHOD
    //                                             ========


}
