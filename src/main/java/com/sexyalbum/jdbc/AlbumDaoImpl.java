package com.sexyalbum.jdbc;

import com.sexyalbum.model.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AlbumDaoImpl implements AlbumDao {
    @Autowired
    private JdbcTemplate template;

    @Override
    public int add(Album album) {
        return template.update("insert into album(albumid, userid, albumname) values(?, ?, ?)",
                album.getAlbumid(), album.getUserid(), album.getAlbumName());
    }

    @Override
    public int update(Album album) {
        return template.update("update album set albumname=? where albumid=?",
                album.getAlbumName(), album.getAlbumid());
    }

    @Override
    public int delete(Album album) {
        return template.update("delete album where albumid=?", album.getAlbumid());
    }

    @Override
    public Album find(Long id) {
        List<Album> album=template.query("select * from album where albumid=?", new Object[]{id},
                new BeanPropertyRowMapper(Album.class));
        if(album!=null&&!album.isEmpty())
            return album.get(0);
        else
            return null;
    }

    @Override
    public List<Album> findUserAlbumsList(Long userid) {
        List<Album> albums=template.query("select * from album where userid=?", new Object[]{userid},
                new BeanPropertyRowMapper<Album>(Album.class));
        if(albums!=null&&!albums.isEmpty())
            return albums;
        else
            return null;
    }

    @Override
    public List<Album> findWholeAlbumList() {
        List<Album> album=template.query("select * from album", new Object[]{},
                new BeanPropertyRowMapper<Album>(Album.class));
        if(album!=null&&!album.isEmpty())
            return album;
        else
            return null;
    }
}
