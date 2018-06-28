package com.sexyalbum.jdbc;

import com.sexyalbum.model.AlbumElementable;

import java.util.List;

public interface EleDao {
    int add(AlbumElementable user);
    int update(AlbumElementable user);
    int delete(Long id);
    String find(Long id);

    List<String> findWholeEleList();
}
