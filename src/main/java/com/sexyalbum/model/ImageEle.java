package com.sexyalbum.model;

import com.sexyalbum.utils.constant;

public class ImageEle implements AlbumElementable {
    private static String PATH = constant.IMAGE_PATH;
    private Long imageid;
    private String type;
    private String description;

    public ImageEle(String type, String description) {
        this.imageid = null;
        this.type = type;
        this.description=description;
    }

    @Override
    public Long getEleId() {
        return imageid;
    }

    @Override
    public String getSource() {
        return PATH +imageid+type;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
