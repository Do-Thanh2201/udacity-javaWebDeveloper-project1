package com.udacity.jwdnd.course1.cloudstorage.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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



    //=====================================================
    //                                       EXECUTE METHOD
    //                                             ========

    /**
     * Screen Homepage
     *
     * @return Screen Homepage
     */
    @GetMapping()
    public String getHomePage() {
        return "home";
    }

    //=====================================================
    //                                      INTERNAL METHOD
    //                                             ========


}
