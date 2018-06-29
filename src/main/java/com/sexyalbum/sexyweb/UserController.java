package com.sexyalbum.sexyweb;

import com.sexyalbum.model.User;
import com.sexyalbum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    // get userinfo for current user
    @RequestMapping()
    @ResponseBody
    public User getUserInfo(@SessionAttribute Long userid){
        return userService.getUser(userid);
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
    @RequestMapping(value = "/account/edit")
    public String editUserAccount(){
        return null;
    }
    @RequestMapping(value = "/account/login")
    public String loginUserAccount(){
        return null;
    }
    @RequestMapping(value = "/account/signup")
    @ResponseBody
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
    public String changeUserPassword(){
        return null;
    }
}
