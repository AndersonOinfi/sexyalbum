package com.sexyalbum.model;

import com.sexyalbum.utils.Constant;

public class Ele {
    private String PATH;

    private Long eleid;
    private String source;
    private String description;
    // like ".png"
    private String type;

    // image ele constructor
    public Ele(String type, String description) {
        this.PATH=Constant.IMAGE_PATH;
        this.eleid = null;//todo
        this.type = type;
        //this.source=PATH+ eleid +type;
        this.description=description;
    }

    // todo other ele constructor
    public Ele(String description) {
        this.description = description;
    }

    public String getPATH() {
        return PATH;
    }

    public String getType() {
        return type;
    }

    public Long getEleid() {
        return eleid;
    }

    public void setEleid(Long eleid) {
        this.eleid = eleid;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
