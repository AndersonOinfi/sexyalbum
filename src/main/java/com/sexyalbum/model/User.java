package com.sexyalbum.model;

import java.util.ArrayList;

public class User {
    private Long userid;
    private String username;
    private String password;
    private ArrayList<Album> albums;

    public User() {
    }

    public User(String username, String password) {
        this();
        this.username = username;
        this.password = password;
        //todo
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
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

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(ArrayList<Album> albums) {
        this.albums = albums;
    }
}
