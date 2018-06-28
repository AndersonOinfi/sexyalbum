package com.sexyalbum.model;

import com.sexyalbum.utils.constant;

public class ImageEle implements AlbumElementable {
    private static String PATH = constant.IMAGE_PATH;
    private Long imageid;
    private String source;
    private String description;
    // like ".png"
    private String type;

    public ImageEle(String type, String description) {
        this.imageid = null;//todo
        this.type = type;
        this.source=PATH+imageid+type;
        this.description=description;
    }

    @Override
    public Long getEleId() {
        return imageid;
    }

    @Override
    public String getSource() {
        return source;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
