package com.sexyalbum.model;

import com.sexyalbum.utils.constant;

public class ImageEle implements AlbumElementable {
    private static String path= constant.IMAGE_PATH;
    private Long imageid;
    private String type;
    private String description;

    public ImageEle(Long imageid, String type) {
        this.imageid = imageid;
        this.type = type;
    }

    @Override
    public Long getEleId() {
        return imageid;
    }

    @Override
    public String getSource() {
        return path+imageid+type;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
