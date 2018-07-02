package com.sexyalbum.service;

import com.sexyalbum.jdbc.AlbumDao;
import com.sexyalbum.jdbc.EleDao;
import com.sexyalbum.jdbc.RelationDao;
import com.sexyalbum.model.Album;
import com.sexyalbum.model.Ele;
import com.sexyalbum.model.Relation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Repository
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private RelationDao relationDao;
    @Autowired
    private EleDao eleDao;

    @Override
    public Long createAlbum(Album album) {
        return albumDao.add(album);
    }

    @Override
    public int updateAlbum(Album album) {
        return albumDao.update(album);
    }

    @Override
    public int deleteAlbum(Long albumid) {
        int rs1=albumDao.delete(albumid);
        int rs2=relationDao.deleteAlbumEles(albumid);
        return rs1&rs2;
    }

    @Override
    public Album getAlbum(Long albumid) {
        Album album=albumDao.find(albumid);
        if(album!=null){
            List<Long> elesid=relationDao.findAlbumElesList(albumid);
            ArrayList<Ele> eles=new ArrayList<>();
            for (Long id:
                 elesid) {
                eles.add(eleDao.find(id));
            }
        }
        return album;
    }

    @Override
    public List<Album> getUserAlbums(Long userid) {
        List<Album> albums=albumDao.findUserAlbumsList(userid);
        if(albums!=null&&!albums.isEmpty()) {
            for (Album album :
                    albums) {
                List<Long> elesid = relationDao.findAlbumElesList(album.getAlbumid());
                ArrayList<Ele> eles = new ArrayList<>();
                if (elesid != null && !elesid.isEmpty()) {
                    for (Long id :
                            elesid) {
                        eles.add(eleDao.find(id));
                    }
                }
                album.setEleList(eles);
            }
        }
        return albums;
    }

    @Override
    public List<Ele> getUserEles(Long userid) {
        List<Album> albums=albumDao.findUserAlbumsList(userid);
        ArrayList<Ele> eles=new ArrayList<>();
        if(albums!=null&&!albums.isEmpty()) {
            for (Album album :
                    albums) {
                List<Long> elesid = relationDao.findAlbumElesList(album.getAlbumid());
                if (elesid != null && !elesid.isEmpty()) {
                    for (Long id :
                            elesid) {
                        eles.add(eleDao.find(id));
                    }
                }
            }
        }
        eles.sort(new Comparator<Ele>() {
            @Override
            public int compare(Ele o1, Ele o2) {
                return o1.getEleid().compareTo(o2.getEleid());
            }
        });
        return eles.subList(0,eles.size());
    }

    @Override
    public Long addAlbumEle(Long albumid, Ele ele) {
        Long eleid=eleDao.add(ele);
        if(eleid!=null){
            relationDao.add(new Relation(albumid,eleid));
        }
        return eleid;
    }

    @Override
    public int deleteAlbumEle(Long albumid, Long eleid) {
        return relationDao.delete(new Relation(albumid,eleid));
    }
}
