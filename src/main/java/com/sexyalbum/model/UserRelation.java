package com.sexyalbum.model;

public class UserRelation {
    private Long userid;
    private Long friendid;

    public UserRelation(Long userid, Long friendid) {
        this.userid = userid;
        this.friendid = friendid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getFriendid() {
        return friendid;
    }

    public void setFriendid(Long friendid) {
        this.friendid = friendid;
    }
}
