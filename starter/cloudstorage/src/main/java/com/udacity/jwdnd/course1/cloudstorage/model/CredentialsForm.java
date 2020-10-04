package com.udacity.jwdnd.course1.cloudstorage.model;

public class CredentialsForm {
    private String username;

    public String getCredentialid() {
        return credentialid;
    }

    public void setCredentialid(String credentialid) {
        this.credentialid = credentialid;
    }

    private String credentialid;

    public CredentialsForm(String username, String password, String url) {
        this.username = username;
        this.password = password;
        this.url = url;
    }

    public CredentialsForm() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String password;
    private String url;

}
