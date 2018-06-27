package com.sexyalbum.jdbc;

import com.sexyalbum.model.AlbumElementable;

import java.util.List;

public interface EleDao {
    int add(AlbumElementable user);
    int update(AlbumElementable user);
    int delete(Long id);
    AlbumElementable find(Long id);
    List<AlbumElementable> findAlbumElesList(Long albumid);

    List<AlbumElementable> findWholeEleList(Long albumid);
}
