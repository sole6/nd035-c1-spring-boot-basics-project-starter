package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileUploadService {

    public final FileMapper fileMapper;

    public List<File> files;

    @PostConstruct
    public void postConstruct() {
        this.files = new ArrayList<>();
    }

    public FileUploadService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public int uploadFile(File file) {
        return fileMapper.insert(file);
    }

    public int deleteFile(int fileId) {
        return fileMapper.deleteFile(fileId);
    }
    public List<File>  getFiles () {
        return  fileMapper.getFiles();
    }

    public File getFile (int fileId){
        return fileMapper.getFile(fileId);
    }
    public boolean isFileNameAvailable(String fileName) {
        return fileMapper.getFileByName(fileName) == null;
    };

}
