package com.sexyalbum.sexyweb;

import com.sexyalbum.model.Album;
import com.sexyalbum.model.Ele;
import com.sexyalbum.model.User;
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

    // add a friend/following
    @RequestMapping(value = "/follow")
    public int follow(@RequestParam(name = "friendid") Long friendid,
                      @SessionAttribute(name = "currentuser") User currentuser) {
        return userService.addFriend(currentuser.getUserid(), friendid);
    }

    // get your followers' list
    @RequestMapping(value = "/followers")
    public List<Long> getUserFollowers(@SessionAttribute(name = "currentuser") User currentuser){
        // todo
        return userService.getFollowers(currentuser.getUserid());
    }

    // get your following's list
    @RequestMapping(value = "/followings")
    public List<Long> getUserFollowing(@SessionAttribute(name = "currentuser") User currentuser){
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
                                  @SessionAttribute User currentUser){
        currentUser.setPassword(password);
        return userService.updateUser(currentUser);
    }

    // user album management
    @RequestMapping(value = "/album")
    public Album getAlbumInfo(@RequestParam Long albumid){
        return albumService.getAlbum(albumid);
    }
    @RequestMapping(value = "/album/create")
    public Long createAlbum(@RequestParam(name = "albumname") String albumName,
                            @SessionAttribute User currentuser){
        return albumService.createAlbum(new Album(currentuser.getUserid(),albumName));
    }
    @RequestMapping(value = "/album/delete")
    public int deleteAlbum(@RequestParam(name = "albumid") Long albumid){
        return albumService.deleteAlbum(albumid);
    }
    @RequestMapping(value = "/album/addele")
    public Long addAlbumEle(@RequestParam(name = "albumid") Long albumid,
                            @RequestParam(name = "ele-file") MultipartFile file,
                            @RequestParam(name = "description") String eleDescription){
        String type=file.getContentType().replaceAll("image/","");
        Ele ele=new Ele(type,eleDescription);
        if(ele.getEleid()!=null) {
            try {
                FileSaver.Save(file.getBytes(), ele.getSource());
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return albumService.addAlbumEle(albumid,ele);
    }
}
