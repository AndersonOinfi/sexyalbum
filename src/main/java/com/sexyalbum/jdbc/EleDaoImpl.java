package com.sexyalbum.jdbc;

import com.sexyalbum.model.AlbumElementable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class EleDaoImpl implements EleDao {
    @Autowired
    private JdbcTemplate template;

    @Override
    public int add(AlbumElementable ele) {
        return template.update("insert into album(id, source, description) values(?, ?, ?)",
                ele.getEleId(), ele.getSource(), ele.getDescription());
    }

    @Override
    public int update(AlbumElementable ele) {
        //todo
        return 0;
    }

    @Override
    public int delete(Long id) {
        //todo
        return 0;
    }

    //todo
    @Override
    public AlbumElementable find(Long id) {
        return null;
    }

    @Override
    public List<AlbumElementable> findAlbumElesList(Long albumid) {
        return null;
    }

    @Override
    public List<AlbumElementable> findWholeEleList(Long albumid) {
        return null;
    }
}
