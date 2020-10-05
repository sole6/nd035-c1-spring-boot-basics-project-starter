package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.SignUpForm;
import com.udacity.jwdnd.course1.cloudstorage.services.SignUpService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

        return "signup";
    }
    @PostMapping
    public String signUp(@ModelAttribute("signupForm") SignUpForm signUpForm, Model model) {
        String signupError = null;
        Boolean signUpSuccess = false;

        if (!userService.isUserNameAvailable(signUpForm.getUsername())) {
            signupError = "User name is already available!";
            signUpSuccess= false;
            model.addAttribute("signUpSuccess", signUpSuccess);
            model.addAttribute("signUpFailure", signupError);

            signUpForm.setSignUpFailure(signupError);
            signUpForm.setSignUpSuccess(signUpSuccess);

        }
        if(signupError==null){
           int rowAdded  = signUpService.signUp(signUpForm);

            if (rowAdded < 0) {
                signupError= "Sign up failed!";
                signUpSuccess= false;
                model.addAttribute("signUpFailure", signupError);
                model.addAttribute("signUpSuccess", signUpSuccess);

                System.out.println(rowAdded);
                System.out.println(signupError);
                System.out.println(signUpSuccess);
                signUpForm.setSignUpFailure(signupError);
                signUpForm.setSignUpSuccess(signUpSuccess);
            }else{
                signupError= null;
                signUpSuccess= true;
                model.addAttribute("signUpSuccess", signUpSuccess);
                System.out.println(rowAdded);
                System.out.println(signupError);
                System.out.println(signUpSuccess);

                signUpForm.setSignUpFailure(signupError);
                signUpForm.setSignUpSuccess(signUpSuccess);
            }
        }


        return "signup";
    }
}
