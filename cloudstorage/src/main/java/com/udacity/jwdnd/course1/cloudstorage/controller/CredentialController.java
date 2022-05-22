package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    /** */

    //=====================================================
    //                                                   DI
    //                                             ========

    /** credentialService*/
    private CredentialService credentialService;

    public CredentialController(CredentialService credentialService) {
        this.credentialService = credentialService;
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
     * Screen Add new the Credential
     *
     * @return Screen Homepage
     */
    @PostMapping()
    public String addCredential() {
        return "redirect:/home";
    }

    /**
     * Screen Edit the Credential
     *
     * @return Screen Homepage
     */
    @PutMapping()
    public String editCredential() {
        return "redirect:/home";
    }

    /**
     * Screen delete the Credential
     *
     * @return Screen Homepage
     */
    @DeleteMapping()
    public String deleteCredential() {
        return "redirect:/home";
    }

    //=====================================================
    //                                      INTERNAL METHOD
    //                                             ========

}
