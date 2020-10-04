package com.udacity.jwdnd.course1.cloudstorage.model;

import javax.persistence.*;

@Table(name = "USERS")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userid;

    private String lastname;

    public User() {

    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    private String firstname;
    private String password;
    private String salt;
    private String username;

    public User(Integer userid, String lastname, String firstname, String password, String salt, String username) {
        this.userid = userid;
        this.lastname = lastname;
        this.firstname = firstname;
        this.password = password;
        this.salt = salt;
        this.username = username;
    }

    public User( String lastname, String firstname, String password, String salt, String username) {

        this.lastname = lastname;
        this.firstname = firstname;
        this.password = password;
        this.salt = salt;
        this.username = username;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public Integer getUserid() {
        return userid;
    }

    public void setUserid(final Integer userid) {
        this.userid = userid;
    }



}
