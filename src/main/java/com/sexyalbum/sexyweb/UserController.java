package com.sexyalbum.sexyweb;

import com.sexyalbum.model.User;
import com.sexyalbum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    // get userinfo for current user
    @RequestMapping()
    @ResponseBody
    public User getUserInfo(@SessionAttribute User currentuser){
        return currentuser;
    }

    // get your followers' list
    @RequestMapping(value = "/followers")
    public String getUserFollowers(){
        return null;
    }

    // get your following's list
    @RequestMapping(value = "/following")
    public String getUserFollowing(){
        return null;
    }

    // get your saved eles' list
    @RequestMapping(value = "/saved")
    public String getUserSavedList(){
        return null;
    }

    // get other users' info
    @RequestMapping(value = "/other")
    public String getOtherUserInfo(){
        return null;
    }

    // current user account management
    // nothing to edit now
    @RequestMapping(value = "/account/edit")
    public String editUserAccount(){
        return null;
    }
    @RequestMapping(value = "/account/login")
    public Long loginUserAccount(@RequestParam String username, @RequestParam String password, HttpSession session){
        User user=new User(username,password);
        Long userid=userService.verifyUser(user);
        if(userid!=null) {
            user.setUserid(userid);
            session.setAttribute("currentuser",user);
        }
        return userid;
    }
    @RequestMapping(value = "/account/signup")
    public Long signupUserAccount(@RequestParam String username, @RequestParam String password, HttpSession session){
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
    public int changeUserPassword(@RequestParam String password, @SessionAttribute User currentUser){
        currentUser.setPassword(password);
        return userService.updateUser(currentUser);
    }
}
