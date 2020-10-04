package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.repository.CredentialsRepository;
import com.udacity.jwdnd.course1.cloudstorage.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialsService {
    @Autowired
    CredentialsRepository credentialsRepository;

    private final EncryptionService encryptionService;
    private final CredentialsMapper credentialsMapper;
    public List<Credentials> credentials;

    public CredentialsService(CredentialsMapper credentialsMapper, EncryptionService encryptionService) {
        this.credentialsMapper = credentialsMapper;
        this.encryptionService = encryptionService;
    }
    @PostConstruct
    public void postConstruct() {
        this.credentials = new ArrayList<>();
    }


    public List<Credentials> getCredentials() {return credentialsMapper.getCredentials();};
    public Credentials getCredential(int credentialid) {return credentialsMapper.getCredential(credentialid);};
    public int deleteCredentials(int credentialid) {
        return credentialsMapper.deleteCredential(credentialid);
    }


    public int updateCredential(Credentials credentials) {
        credentials = encreptyCredentials(credentials);
        return credentialsMapper.updateCredential(credentials);
    }

    public int saveCredentials(Credentials credentials) {
        credentials = encreptyCredentials(credentials);
        return credentialsMapper.insertCredentials(credentials);
    }

    private Credentials encreptyCredentials(Credentials credentials) {
        String encodedKey = encryptionService.generateEncrKey();
        String encryptedPassword = encryptionService.encryptValue(credentials.getPassword(), encodedKey);
        credentials.setKey(encodedKey);
        credentials.setPassword(encryptedPassword);
        return credentials;
    }
}
