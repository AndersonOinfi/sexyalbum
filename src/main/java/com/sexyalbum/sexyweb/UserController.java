package com.sexyalbum.sexyweb;

import com.sexyalbum.model.*;
import com.sexyalbum.service.AlbumService;
import com.sexyalbum.service.UserService;
import com.sexyalbum.utils.FileSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AlbumService albumService;

    // get userinfo for current user
    @RequestMapping(value = "")
    public User getUserInfo(@SessionAttribute User currentuser){
        User user=new User(currentuser);
        ArrayList<Album> albums=new ArrayList<>(albumService.getUserAlbums(user.getUserid()));
        user.setAlbums(albums);
        return user;
    }

    @RequestMapping(value = "/skech")
    public User getUserSkech(@RequestParam(name = "userid", required = false, defaultValue = "") Long userid,
                             @SessionAttribute(name = "currentuser") User currentuser) {
        if(userid==null)
            userid=currentuser.getUserid();
        return userService.getUser(userid);
    }

    // get messages
    @RequestMapping(value = "/messages")
    public List<Message> getUnreadMessages(@SessionAttribute(name = "currentuser") User currentuser) {
        return userService.getUserUnreadMessages(currentuser.getUserid());
    }

    @RequestMapping(value = "/main")
    public List<Message> getMainMessages(@SessionAttribute(name = "currentuser") User currentuser) {
        return userService.getUserMainMessages(currentuser.getUserid());
    }

    // like a ele
    @RequestMapping(value = "/like")
    public int like(@RequestParam(name = "eleid") Long eleid,
                    @SessionAttribute(name = "currentuser") User currentuser) {
        return userService.likeEle(currentuser.getUserid(), eleid);
    }

    @RequestMapping(value = "/like/cancel")
    public int cancelLike(@RequestParam(name = "eleid") Long eleid,
                          @SessionAttribute(name = "currentuser") User currentuser) {
        return userService.cancelLike(currentuser.getUserid(), eleid);
    }

    @RequestMapping(value = "/like/likes")
    public List<Long> getLikes(@RequestParam(name = "userid", required = false, defaultValue = "") Long userid,
                               @SessionAttribute(name = "currentuser") User currentuser) {
        if(userid==null)
            userid=currentuser.getUserid();
        return userService.getUserLikes(userid);
    }

    @RequestMapping(value = "/like/likers")
    public List<Long> getLikers(@RequestParam(name = "eleid") Long eleid) {
        // todo
        return userService.getEleLikers(eleid);
    }

    // comment a ele
    @RequestMapping(value = "/comment")
    public Long comment(@RequestParam(name = "eleid") Long eleid,
                        @RequestParam(name = "comments") String comments,
                        @RequestParam(name = "tarid") Long tarid,
                        @SessionAttribute(name = "currentuser") User currentuser) {
        Long commentid=albumService.addCommentToEle(new Comment(eleid, currentuser.getUserid(), tarid, comments));
        return commentid;
    }

    // add a friend/following
    @RequestMapping(value = "/follow")
    public int follow(@RequestParam(name = "friendid") Long friendid,
                      @SessionAttribute(name = "currentuser") User currentuser) {
        int rs=userService.addFriend(currentuser.getUserid(), friendid);
        if(rs>0)
            userService.createMessage(new Message(currentuser.getUserid(), friendid, Message.FOLLOW_MESSAGE, friendid));
        return rs;
    }

    // get your followers' list
    @RequestMapping(value = "/followers")
    public List<Long> getUserFollowers(@RequestParam(name = "userid", required = false, defaultValue = "") Long userid,
                                       @SessionAttribute(name = "currentuser") User currentuser){
        if(userid==null)
            userid=currentuser.getUserid();
        // todo
        return userService.getFollowers(userid);
    }

    // get your following's list
    @RequestMapping(value = "/followings")
    public List<Long> getUserFollowing(@RequestParam(name = "userid", required = false, defaultValue = "") Long userid,
                                       @SessionAttribute(name = "currentuser") User currentuser){
        if(userid==null)
            userid=currentuser.getUserid();
        // todo
        return userService.getFollowings(currentuser.getUserid());
    }

    // get your saved eles' list
    @RequestMapping(value = "/saved")
    public String getUserSavedList(){
        return null;
    }

    // get other users' info
    @RequestMapping(value = "/other")
    public User getOtherUserInfo(@RequestParam(name = "username") String otherUsername){
        return userService.getUser(otherUsername);
    }

    // current user account management
    // nothing to edit now
    @RequestMapping(value = "/account/edit")
    public String editUserAccount(){
        // todo update session
        return null;
}
    @RequestMapping(value = "/account/login")
    public Long loginUserAccount(@RequestParam(name = "username") String username,
                                 @RequestParam(name = "password") String password,
                                 HttpSession session){
        User user=new User(username,password);
        Long userid=userService.verifyUser(user);
        if(userid!=null) {
            user.setUserid(userid);
            List<Long> friends=userService.getFollowers(userid);
            if(friends!=null)
                user.setFriends(new ArrayList<>(friends));
            session.setAttribute("currentuser",user);
        }
        return userid;
    }
    @RequestMapping(value = "/account/signup")
    public Long signupUserAccount(@RequestParam(name = "username") String username,
                                  @RequestParam(name = "password") String password,
                                  HttpSession session){
        User user=new User(username,password);
        Long userid=userService.createUser(user);
        if(userid!=null){
            user.setUserid(userid);
            session.setAttribute("currentuser",user);
        }
        return userid;
    }

    // user password management
    @RequestMapping(value = "/password/change")
    public int changeUserPassword(@RequestParam(name = "password") String password,
                                  @SessionAttribute(name = "currentuser") User currentUser){
        currentUser.setPassword(password);
        return userService.updateUser(currentUser);
    }

    // user album management
    @RequestMapping(value = "/album")
    public Album getAlbumInfo(@RequestParam(name = "albumid") Long albumid){
        return albumService.getAlbum(albumid);
    }
    @RequestMapping(value = "/album/create")
    public Long createAlbum(@RequestParam(name = "albumname") String albumName,
                            @SessionAttribute(name = "currentuser") User currentuser){
        Long albumid=albumService.createAlbum(new Album(currentuser.getUserid(),albumName));
        if(albumid!=null) {
            Long userid=currentuser.getUserid();
            userService.createMessage(new Message(userid, userid, Message.ALBUM_MESSAGE, albumid));
            List<Long> friends=currentuser.getFriends();
            if(friends!=null) {
                for (Long friendid:
                        friends) {
                    userService.createMessage(new Message(userid, friendid, Message.ALBUM_MESSAGE, albumid));
                }
            }
        }
        return albumid;
    }
    @RequestMapping(value = "/album/delete")
    public int deleteAlbum(@RequestParam(name = "albumid") Long albumid){
        return albumService.deleteAlbum(albumid);
    }
    @RequestMapping(value = "/album/addele")
    public Long addAlbumEle(@RequestParam(name = "albumid") Long albumid,
                            @RequestParam(name = "ele-file") MultipartFile file,
                            @RequestParam(name = "description") String eleDescription,
                            @SessionAttribute(name = "currentuser") User currentuser) {
        // todo else file types
        String type = file.getContentType().replaceAll("image/", "");
        Ele ele = new Ele(type, eleDescription);
        try {
            FileSaver.Save(file.getBytes(), ele.getPrePath() + ele.getSource());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        Long eleid=albumService.addAlbumEle(albumid, ele);
        Long userid = currentuser.getUserid();
        userService.createMessage(new Message(userid, userid, Message.ALBUM_MESSAGE, eleid));
        List<Long> friends = currentuser.getFriends();
        if (friends != null) {
            for (Long friendid :
                    friends) {
                userService.createMessage(new Message(userid, friendid, Message.ALBUM_MESSAGE, albumid));
            }
        }
        return eleid;
    }
}
