package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class NotesController {

    private final NoteService noteService;
    private final UserService userService;
    private final ModelInitializerService modelInitializerService;
    public NotesController(NoteService noteService, UserService userService, ModelInitializerService modelInitializerService) {
        this.noteService = noteService;
        this.userService = userService;
        this.modelInitializerService = modelInitializerService;
    }

    @GetMapping("/notes")
    public String getFileUpload ( Model model) {
        modelInitializerService.initModels(model);
        return "home";
    }
    @PostMapping("/notes/add")
    public String addNote(@ModelAttribute("noteForm") NoteForm noteForm, Model model) {
        modelInitializerService.initModels(model);
        System.out.println(new StringBuilder().append("note id ").append(noteForm.getNoteId()).toString());
        if(noteForm.getNoteId()!=""){
            int up= editNote(noteForm);
            if(up>0){
                model.addAttribute("listOfNotes", this.noteService.getNotes());
                model.addAttribute("success", "You have successfully updated " + noteForm.getNotetitle());
                model.addAttribute("failure", null);
                model.addAttribute("route", "/notes");
            }else{
                model.addAttribute("success", null);
                model.addAttribute("failure", "Failed to edit note: " + noteForm.getNotetitle());
                model.addAttribute("route", "/notes");
            }
        }
        if(noteForm.getNoteId()==""){
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Note note = new Note();
            note.setNotetitle(noteForm.getNotetitle());
            note.setNotedescription(noteForm.getNotedescription());
            note.setUserid(userService.getUser(auth.getName()).getUserid());
            int rowAdded  = noteService.addNote(note);
            if(rowAdded>0){
                model.addAttribute("listOfNotes", this.noteService.getNotes());
                model.addAttribute("success", "Successfully added note: " + noteForm.getNotetitle());
                model.addAttribute("failure", null);
                model.addAttribute("route", "/notes");
            }else {
                model.addAttribute("success", null);
                model.addAttribute("failure", "Failed to add note: "+ noteForm.getNotetitle());
                model.addAttribute("route", "/notes");
            }
        }
        return "result";
    }

    @RequestMapping(value="/notes/delete")
    public String deleteNote(@Param(value="noteid") int noteid, Model model) {
        modelInitializerService.initModels(model);
        int deletestatus = noteService.deleteNote(noteid);
        if(deletestatus>0){
            model.addAttribute("success", "Successfully deleted a note");
            model.addAttribute("failure", null);
            model.addAttribute("route", "/notes");
        }else{
            model.addAttribute("success", null);
            model.addAttribute("failure", "Failed to delete a note");
            model.addAttribute("route", "/notes");
        }
        return "result";
    }

    private int editNote(NoteForm noteForm) {
        Note note = noteService.getNote(Integer.parseInt(noteForm.getNoteId()));
        note.setNotedescription(noteForm.getNotedescription());
        note.setNotetitle(noteForm.getNotetitle());
        System.out.println(note.getNoteid());
        int rowUpdated = noteService.updateNote(note);
        return rowUpdated;
    }
}
