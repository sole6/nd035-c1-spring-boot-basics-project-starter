package com.udacity.jwdnd.course1.cloudstorage.model;

public class SignUpForm {
    private String firstname;
    private String lastname;
    private String username;
    private String password;



    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }


    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public Boolean getSignUpSuccess() {
        return signUpSuccess;
    }

    public void setSignUpSuccess(Boolean signUpSuccess) {
        this.signUpSuccess = signUpSuccess;
    }

    private Boolean signUpSuccess;

    public String getSignUpFailure() {
        return signUpFailure;
    }

    public void setSignUpFailure(String signUpFailure) {
        this.signUpFailure = signUpFailure;
    }

    private String signUpFailure;

}