package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.LoginForm;
import com.udacity.jwdnd.course1.cloudstorage.model.SignUpForm;
import com.udacity.jwdnd.course1.cloudstorage.services.SignUpService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    private final SignUpService signUpService;
    private final UserService userService;

    public SignUpController(SignUpService signUpService, UserService userService) {
        this.signUpService = signUpService;
        this.userService = userService;
    }


    @GetMapping
    public String getSignUpPage (@ModelAttribute("signupForm") SignUpForm signUpForm, Model model) {

        model.addAttribute("signUpFailure", null);
        signUpForm.setSignUpFailure(null);
        return "signup";
    }
    @PostMapping
    public String signUp(@ModelAttribute("signupForm") SignUpForm signUpForm, Model model, RedirectAttributes attr) {
        String signupError = null;
        Boolean signUpSuccess = false;
        String page = "signup";
        // fail if user name is not available
        if (!userService.isUserNameAvailable(signUpForm.getUsername())) {
            signupError = "User name is already available!";
            signUpSuccess= false;
            model.addAttribute("signUpSuccess", signUpSuccess);
            model.addAttribute("signUpFailure", signupError);

            signUpForm.setSignUpFailure(signupError);
            page = "signup";

        }
        if(signupError==null){
           int rowAdded  = signUpService.signUp(signUpForm);
            // fail if user account can be be saved
            if (rowAdded < 0) {
                signupError= "Sign up failed!";
                signUpSuccess= false;
                model.addAttribute("signUpFailure", signupError);
                signUpForm.setSignUpFailure(signupError);

                page = "signup";
            }else{
                // show success msg if user account is saved
                // go to login page
                signupError= null;
                signUpSuccess= true;
                attr.addFlashAttribute("signUpSuccess", signUpSuccess);
                page = "redirect:/login";
            }
        }

        return page;
    }

    public ModelAndView redirectWithUsingRedirectPrefix(ModelMap model) {
        return new ModelAndView("redirect:/login", model);
    }
}
