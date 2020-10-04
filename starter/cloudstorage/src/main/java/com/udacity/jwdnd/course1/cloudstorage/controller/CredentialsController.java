package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialsForm;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CredentialsController {

    private final UserService userService;
    private final CredentialsService credentialsService;
    private final EncryptionService encryptionService;
    private final ModelInitializerService modelInitializerService;

    public CredentialsController(UserService userService, CredentialsService credentialsService, EncryptionService encryptionService, ModelInitializerService modelInitializerService) {
        this.userService = userService;
        this.credentialsService = credentialsService;
        this.encryptionService = encryptionService;
        this.modelInitializerService = modelInitializerService;
    }
    @GetMapping("/credentials")
    public String getCredentials ( Model model) {
        modelInitializerService.initModels(model);
        return "home";
    }
    @PostMapping("/credentials")
    public String addCrendetials(@ModelAttribute("credentialForm") CredentialsForm credentialsForm, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        modelInitializerService.initModels(model);
        String message="";
        int rowSaved = 0;
        Credentials credentials = new Credentials();
        credentials.setUrl(credentialsForm.getUrl());
        credentials.setUserid(userService.getUser(auth.getName()).getUserid());
        credentials.setUsername(credentialsForm.getUsername());
        credentials.setPassword(credentialsForm.getPassword());
        if(credentialsForm.getCredentialid() != "") {
            credentials.setCredentialid(Integer.parseInt(credentialsForm.getCredentialid()));
            rowSaved = updateCredential(credentials);
            message = "updated credentails for ";
        }else{
            rowSaved=credentialsService.saveCredentials(credentials);
            message = "added credentails for ";
        }
        if(rowSaved>0){
            model.addAttribute("success", "You have successfully " + message+ credentials.getUsername());
            model.addAttribute("failure", null);
        }else{
            model.addAttribute("success", null);
            model.addAttribute("failure", "Failed to add/edit credential for "+credentials.getUsername());
        }

        model.addAttribute("route", "/credentials");
        return "result";
    }

    private int updateCredential (Credentials credentials) {
        return credentialsService.updateCredential(credentials);
    }
    @RequestMapping(value="/credentials/delete")
    public String deleteCredentials(@Param(value="credentialid") int credentialid, Model model) {
        modelInitializerService.initModels(model);
        int rowDeleted = credentialsService.deleteCredentials(credentialid);
        if(rowDeleted>0){
            model.addAttribute("success", "You have successfully deleted credential");
            model.addAttribute("failure", null);
        }else{
            model.addAttribute("success", null);
            model.addAttribute("failure", "Failed to delete credential");
        }

        model.addAttribute("route", "/credentials");
        return "result";
    }

    @GetMapping(value="/credentials/descreptCredential")
    @ResponseBody
    public Credentials descreptCredential(@Param(value="credentialid") int credentialid) {
       // modelInitializerService.initModels(model);
        System.out.println(credentialid);
        Credentials credential = credentialsService.getCredential(credentialid);
        String decrepredPass = encryptionService.decryptValue(credential.getPassword(), credential.getKey());
        System.out.println("contr" + decrepredPass);
        credential.setPassword(decrepredPass);
        //model.addAttribute("listOfCredentials", this.credentialsService.getCredentials());
        return credential;
    }

}
