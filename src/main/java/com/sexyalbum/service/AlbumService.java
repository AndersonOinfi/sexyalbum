package com.sexyalbum.service;

import com.sexyalbum.model.Album;
import com.sexyalbum.model.Ele;

import java.util.List;

public interface AlbumService {
    // 创建相册
    Long createAlbum(Album album);
    // 修改相册名字
    int updateAlbum(Album album);
    // 删除指定相册
    int deleteAlbum(Long albumid);
    // 获取指定相册
    Album getAlbum(Long albumid);
    // 获取指定用户的所有相册
    List<Album> getUserAlbums(Long userid);
    // 向指定相册中添加元素
    Long addAlbumEle(Long albumid,Ele ele);
    // 删除指定相册中的指定元素
    int deleteAlbumEle(Long albumid,Long eleid);
}
