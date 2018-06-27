package com.sexyalbum.model;

import java.util.ArrayList;

public class Album {
    Long albumid;
    Long userid;
    String albumName;
    ArrayList<AlbumElementable> eleList;

    public Album(){}

    public Album(Long userid,String albumName){
        this.userid=userid;
        this.albumName=albumName;
        eleList=new ArrayList<>();
        //todo
    }

    public Long getAlbumid() {
        return albumid;
    }

    public void setAlbumid(Long albumid) {
        this.albumid = albumid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public ArrayList<AlbumElementable> getEleList() {
        return eleList;
    }

    public void setEleList(ArrayList<AlbumElementable> eleList) {
        this.eleList = eleList;
    }
}
