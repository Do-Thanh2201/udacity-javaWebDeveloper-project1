package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.dto.CredentialDTO;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * CredentialController
 *
 * @author Do Thanh
 */
@Controller
@RequestMapping("/credential")
public class CredentialController {

    //=====================================================
    //                                             CONSTANT
    //                                             ========

    /** Empty Uploaded Credential message */
    private static final String EMPTY_CREDENTIAL_MESSAGE = "Please fill a Credential to save.";

    /** Create Credential successfully message */
    private static final String CREATE_CREDENTIAL_SUCCESS_MESSAGE = "You successfully Create the Credential!";

    /** Update Credential successfully message */
    private static final String UPDATE_CREDENTIAL_SUCCESS_MESSAGE    = "You successfully update the Credential!";

    /** Delete Credential successfully message */
    private static final String DELETE_CREDENTIAL_SUCCESS_MESSAGE    = "You successfully delete the Credential!";

    /** Create Credential error message */
    private static final String CREATE_CREDENTIAL_ERROR_MESSAGE = "An error occurred while Creating the Credential!";

    /** Update Credential error message */
    private static final String UPDATE_CREDENTIAL_ERROR_MESSAGE      = "An error occurred while updating the Credential!";

    /** Delete Credential error message */
    private static final String DELETE_CREDENTIAL_ERROR_MESSAGE      = "An error occurred while deleting the Credential!";

    //=====================================================
    //                                                   DI
    //                                             ========

    /** credentialService*/
    private CredentialService credentialService;

    /** userService*/
    private UserService userService;

    public CredentialController(CredentialService credentialService, UserService userService) {
        this.credentialService = credentialService;
        this.userService = userService;
    }

    //=====================================================
    //                                       EXECUTE METHOD
    //                                             ========

    /**
     * Screen view the list Credential
     *
     * @return Screen Homepage
     */
    @GetMapping
    public String getCredential() {
        return "redirect:/home";
    }

    /**
     * Screen Update the Credential
     *
     * @return Screen Homepage
     */
    @PostMapping()
    public String updateCredential(@ModelAttribute("credential") CredentialDTO credentialDTO, RedirectAttributes redirectAttrs) {

        // Check if credential is empty
        if (credentialDTO == null) {

            redirectAttrs.addFlashAttribute("message", EMPTY_CREDENTIAL_MESSAGE);
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

        // Check if credentialId != 0 then edit the Credential
        if (credentialDTO.getCredentialId() != 0 ) {

            // Edit the Credential
            editCredential(credentialDTO, userId, redirectAttrs);
            return "redirect:/home";
        }

        // Else Create a new Credential
        createCredential(credentialDTO, userId, redirectAttrs);

        return "redirect:/home";
    }


    /**
     * Screen delete the Credential
     *
     * @return Screen Homepage
     */
    @GetMapping("/delete")
    public String deleteCredential(@RequestParam("credentialId") int credentialId, RedirectAttributes redirectAttrs) {

        if (!credentialService.delete(credentialId)) {

            // return error response
            redirectAttrs.addFlashAttribute("message", DELETE_CREDENTIAL_ERROR_MESSAGE);
            return "redirect:/home";
        };

        // return success response
        redirectAttrs.addFlashAttribute("message", DELETE_CREDENTIAL_SUCCESS_MESSAGE);
        return "redirect:/home";
    }

    //=====================================================
    //                                      INTERNAL METHOD
    //                                             ========

    /**
     * Edit the Credential
     *
     * @param credentialDTO: Credential information
     * @param redirectAttrs: Set message for redirect
     */
    public void editCredential(CredentialDTO credentialDTO, int userId, RedirectAttributes redirectAttrs) {

        Credential credential = credentialService.toCredential
                (credentialDTO.getUrl(), credentialDTO.getUsername(), credentialDTO.getDecryptPassword(), userId);
        credential.setCredentialId(credentialDTO.getCredentialId());

        if (!credentialService.update(credential)) {

            // return error response
            redirectAttrs.addFlashAttribute("message", UPDATE_CREDENTIAL_ERROR_MESSAGE);
            return;
        };

        redirectAttrs.addFlashAttribute("message", UPDATE_CREDENTIAL_SUCCESS_MESSAGE);
    }

    /**
     * Create the Credential
     *
     * @param credentialDTO: Note information
     * @param redirectAttrs: Set message for redirect
     */
    public void createCredential(CredentialDTO credentialDTO, int userId, RedirectAttributes redirectAttrs) {


        Credential credential = credentialService.toCredential
                (credentialDTO.getUrl(), credentialDTO.getUsername(), credentialDTO.getDecryptPassword(), userId);

        if (!credentialService.insert(credential)) {

            // return error response
            redirectAttrs.addFlashAttribute("message", CREATE_CREDENTIAL_ERROR_MESSAGE);
            return;
        };

        redirectAttrs.addFlashAttribute("message", CREATE_CREDENTIAL_SUCCESS_MESSAGE);
    }

}
