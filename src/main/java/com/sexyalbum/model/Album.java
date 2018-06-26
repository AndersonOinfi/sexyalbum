package com.sexyalbum.model;

import java.util.ArrayList;

public class Album {
    String albumName;
    ArrayList<AlbumElementable> eleList;

    public Album(){}

    public Album(String albumName){
        this.albumName=albumName;
        eleList=new ArrayList<>();
        //todo
    }
}
