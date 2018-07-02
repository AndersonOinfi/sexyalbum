package com.sexyalbum.jdbc;

import com.sexyalbum.model.Relation;

import java.util.List;

public interface RelationDao {
    int add(Relation relation);
    int update(Relation relation);
    int delete(Relation relation);
    int deleteAlbumEles(Long albumid);
    List<Long> findAlbumElesList(Long albumid);

    List<Relation> findWholeRelationList();
}
