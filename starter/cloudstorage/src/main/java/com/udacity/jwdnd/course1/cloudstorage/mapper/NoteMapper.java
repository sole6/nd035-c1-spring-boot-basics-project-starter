package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
    @Select("Select * From NOTES WHERE noteid = #{noteid}")
    Note getNote(int noteid);
    @Select("Select * From NOTES")
    List<Note> getNotes();

    @Insert("INSERT INTO NOTES (notetitle, notedescription) VALUES(#{notetitle}, #{notedescription})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    int insertNote (Note note);

    @Update("UPDATE NOTES SET notetitle=#{notetitle} , notedescription =#{notedescription} WHERE noteid=#{noteid}")
    int updateNote (Note note);
    @Delete("DELETE FROM NOTES WHERE noteid = #{noteid}")
    int deleteNote(int noteid);
}
