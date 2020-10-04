package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialsForm;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class ModelInitializerService {
    private final FileUploadService fileUploadService;
    private final NoteService noteService;
    private final CredentialsService credentialsService;
    private  final EncryptionService encryptionService;

    public ModelInitializerService(FileUploadService fileUploadService, NoteService noteService, CredentialsService credentialsService, EncryptionService encryptionService) {
        this.fileUploadService = fileUploadService;
        this.noteService = noteService;
        this.credentialsService = credentialsService;
        this.encryptionService = encryptionService;
    }

    public void initModels(Model model) {
        model.addAttribute("noteForm",new NoteForm());
        model.addAttribute("credentialForm", new CredentialsForm());
        model.addAttribute("encryptionService", encryptionService);
        model.addAttribute("listOfFiles", this.fileUploadService.getFiles());
        model.addAttribute("listOfNotes", this.noteService.getNotes());
        model.addAttribute("listOfCredentials", this.credentialsService.getCredentials());
    }
}
