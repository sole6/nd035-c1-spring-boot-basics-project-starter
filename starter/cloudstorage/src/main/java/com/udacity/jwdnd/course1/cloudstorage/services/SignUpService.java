package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.model.SignUpForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class SignUpService {
    @Autowired
    UserRepository userRepository;
    public SignUpService(UserService userService) {
        this.userService = userService;
    }

    private final UserService userService;


    @PostConstruct
    public void postConstruct() {
        System.out.println("Creating SignUpService bean");
    }

    public int signUp (SignUpForm signUpForm) {

        String signupError = null;

        if (!userService.isUserNameAvailable(signUpForm.getUsername())) {
            signupError = "The username already exists.";
        }
        User user = new User();
        user.setUserid((Integer) 0);
        user.setSalt(null);
        user.setPassword(signUpForm.getPassword());
        user.setFirstname(signUpForm.getPassword());
        user.setLastname(signUpForm.getLastname());
        user.setUsername(signUpForm.getUsername());
        System.out.println(user.getUserid());
       return userService.createUser(user);
    }
}
