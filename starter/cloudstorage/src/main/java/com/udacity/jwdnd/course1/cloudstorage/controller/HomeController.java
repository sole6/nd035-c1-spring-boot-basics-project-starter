package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    private final ModelInitializerService modelInitializerService;

    public HomeController(ModelInitializerService modelInitializerService) {
        this.modelInitializerService = modelInitializerService;
    }

    @GetMapping("/home")
    public String getHomePage (Model model) {
        modelInitializerService.initModels(model);
        return "home";
    }



}
