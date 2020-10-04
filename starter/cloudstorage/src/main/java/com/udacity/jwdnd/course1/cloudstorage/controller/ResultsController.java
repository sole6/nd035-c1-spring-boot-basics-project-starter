package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResultsController {

    @GetMapping("/result")
    public String getResultPage (Model model) {
        model.addAttribute("success", false);
        model.addAttribute("failure", false);
        model.addAttribute("route", "");
        return "result";
    }
}
