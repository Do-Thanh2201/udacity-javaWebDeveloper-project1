package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller()
@RequestMapping("/signup")
public class SignupController {

    //=====================================================
    //                                             CONSTANT
    //                                             ========
    private static final String USER_EXIST_MESSAGE        = "The username already exists.";
    private static final String SIGNUP_SUCCESS_MESSAGE    = "You successfully signed up!";
    private static final String SIGNUP_ERROR_MESSAGE      = "There was an error signing you up. Please try again.";

    /** */

    //=====================================================
    //                                                   DI
    //                                             ========
    /** userService */
    private final UserService userService;

    /** SignupController Constructor */
    public SignupController(UserService userService) {
        this.userService = userService;
    }

    //=====================================================
    //                                       EXECUTE METHOD
    //                                             ========

    /**
     * Screen Signup
     *
     * @return Screen Signup
     */
    @GetMapping()
    public String signupView() {
        return "signup";
    }

    /**
     * Screen Signup
     *
     * @return : Screen Sign-up if signup fail. Screen Login if signup success.
     */
    @PostMapping()
    public String signupUser(@ModelAttribute User user, Model model, RedirectAttributes redirectAttrs) {
        String signupError = null;

        if (!userService.isUsernameAvailable(user.getUsername())) {
            signupError = USER_EXIST_MESSAGE;
        }

        if (signupError == null) {
            int rowsAdded = userService.createUser(user);
            if (rowsAdded < 0) {
                signupError = SIGNUP_ERROR_MESSAGE;
            }
        }

        if (signupError == null) {
            redirectAttrs.addFlashAttribute("message", SIGNUP_SUCCESS_MESSAGE);
            return "redirect:/login";
        } else {
            model.addAttribute("signupError", signupError);
        }
        return "signup";
    }

    //=====================================================
    //                                      INTERNAL METHOD
    //                                             ========


}
