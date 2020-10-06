package com.udacity.jwdnd.course1.cloudstorage.model;

public class LoginForm {
    private String userName;

    private String password;

    private Boolean isValidUser;

    private Boolean isLoggedOut;

    public Boolean getSignUpSuccess() {
        return signUpSuccess;
    }

    public void setSignUpSuccess(Boolean signUpSuccess) {
        this.signUpSuccess = signUpSuccess;
    }

    private Boolean signUpSuccess;
    public Boolean getLoggedOut() {
        return isLoggedOut;
    }

    public void setLoggedOut(Boolean loggedOut) {
        isLoggedOut = loggedOut;
    }


    public Boolean getIsValidUser() {
        return isValidUser;
    }

    public void setIsValidUser(Boolean isValidUser) {
        this.isValidUser = isValidUser;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }





}
