package com.sexyalbum.jdbc;

import com.sexyalbum.model.Ele;

import java.util.List;

public interface EleDao {
    int add(Ele ele);
    int update(Ele ele);
    int delete(Long eleid);
    Ele find(Long eleid);

    List<Ele> findWholeEleList();
}
