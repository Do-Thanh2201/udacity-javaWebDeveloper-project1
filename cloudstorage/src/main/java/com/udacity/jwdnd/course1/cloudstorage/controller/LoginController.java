package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

    //=====================================================
    //                                             CONSTANT
    //                                             ========

    /** */

    //=====================================================
    //                                                   DI
    //                                             ========



    //=====================================================
    //                                       EXECUTE METHOD
    //                                             ========

    /**
     * Screen Login
     *
     * @return Screen Login
     */
    @GetMapping()
    public String loginView() {
        return "login";
    }

    //=====================================================
    //                                      INTERNAL METHOD
    //                                             ========


}
