package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.dto.NoteDTO;
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

    /** Create Note successfully message */
    private static final String CREATE_NOTE_SUCCESS_MESSAGE = "You successfully create the note!";

    /** Update Note successfully message */
    private static final String UPDATE_NOTE_SUCCESS_MESSAGE    = "You successfully update the note!";

    /** Delete Note successfully message */
    private static final String DELETE_NOTE_SUCCESS_MESSAGE    = "You successfully delete the note!";

    /** Create note error message */
    private static final String CREATE_NOTE_ERROR_MESSAGE = "An error occurred while Create the note!";

    /** Update note error message */
    private static final String UPDATE_NOTE_ERROR_MESSAGE      = "An error occurred while updating the note!";

    /** Delete note error message */
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
     * Screen Add new / update the Note
     *
     * @return Screen Homepage
     */
    @PostMapping()
    public String updateNote(@ModelAttribute("note") NoteDTO noteDTO, RedirectAttributes redirectAttrs) {

        // Check if file is empty
        if (noteDTO == null) {

            redirectAttrs.addFlashAttribute("message", EMPTY_NOTE_MESSAGE);
            return "redirect:/home";
        }

        // Get userId

        Integer userId;
        try {
            userId = userService.getUserID();
        }
        catch (Exception e) {
            redirectAttrs.addFlashAttribute("message", UserService.NOT_EXIST_USER_MESSAGE);
            return "redirect:/login";
        }

        // Check if noteId != 0 then edit the note
        if (noteDTO.getNoteId() != 0 ) {

            // Edit the Note
            editNote(noteDTO, userId, redirectAttrs);
            return "redirect:/home";
        }

        // Else Create a new Note
        createNote(noteDTO, userId, redirectAttrs);

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

    /**
     * Edit the Note
     *
     * @param noteDTO: Note information
     * @param redirectAttrs: Set message for redirect
     */
    public void editNote(NoteDTO noteDTO, int userId, RedirectAttributes redirectAttrs) {

        Note note = noteService.setToNote(noteDTO.getNoteTitle(), noteDTO.getNoteDescription(),userId);

        note.setNoteId(noteDTO.getNoteId());

        if (!noteService.edit(note)) {

            // return error response
            redirectAttrs.addFlashAttribute("message", UPDATE_NOTE_ERROR_MESSAGE);
            return;
        };

        redirectAttrs.addFlashAttribute("message", UPDATE_NOTE_SUCCESS_MESSAGE);
    }

    /**
     * Create the Note
     *
     * @param noteDTO: Note information
     * @param redirectAttrs: Set message for redirect
     */
    public void createNote(NoteDTO noteDTO, int userId, RedirectAttributes redirectAttrs) {


        Note note = noteService.setToNote(noteDTO.getNoteTitle(), noteDTO.getNoteDescription(), userId);

        if (!noteService.insert(note)) {

            // return error response
            redirectAttrs.addFlashAttribute("message", CREATE_NOTE_ERROR_MESSAGE);
            return;
        };

        redirectAttrs.addFlashAttribute("message", CREATE_NOTE_SUCCESS_MESSAGE);
    }

}
