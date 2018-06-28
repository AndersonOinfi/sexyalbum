package com.sexyalbum.jdbc;

import com.sexyalbum.model.Album;

import java.util.List;

public interface AlbumDao {
    int add(Album album);
    int update(Album album);
    int delete(Long albumid);
    Album find(Long albumid);
    //todo
    List<Album> findUserAlbumsList(Long userid);


    List<Album> findWholeAlbumList();
}
