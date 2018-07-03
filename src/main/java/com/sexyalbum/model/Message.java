package com.sexyalbum.model;

public class Message {
    private Long messageid;
    private Long userid;
    private Long tarid;
    private boolean flag;
    private int type;
    private Long info;

    public final static int FOLLOW_MESSAGE=1;
    public final static int LIKE_MESSAGE=2;
    public final static int COMMENT_MESSAGE=3;

    public Message() {
    }

    public Message(Long userid, Long tarid, int type, Long info) {
        this.userid = userid;
        this.tarid = tarid;
        this.type=type;
        this.info=info;
        this.messageid=null;
        this.flag=true;
    }

    public Long getMessageid() {
        return messageid;
    }

    public void setMessageid(Long messageid) {
        this.messageid = messageid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getTarid() {
        return tarid;
    }

    public void setTarid(Long tarid) {
        this.tarid = tarid;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Long getInfo() {
        return info;
    }

    public void setInfo(Long info) {
        this.info = info;
    }
}
