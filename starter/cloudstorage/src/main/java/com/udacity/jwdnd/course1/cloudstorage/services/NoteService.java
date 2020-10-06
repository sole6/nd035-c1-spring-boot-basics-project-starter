package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }
    @PostConstruct
    public void postConstruct() {
        this.notes = new ArrayList<>();
    }

    public final NoteMapper noteMapper;

    public List<Note> notes;
    public List<Note> getNotes() {return noteMapper.getNotes();};
    public int addNote(Note note){
        return noteMapper.insertNote(note);
    }
    public int deleteNote(int noteid) {
        return noteMapper.deleteNote(noteid);
    }
    public int updateNote(Note note) {
        System.out.println(note.getNoteid());
        return noteMapper.updateNote(note);
    }

    public Note getNote(int noteid) {
        return noteMapper.getNote(noteid);
    }

}
