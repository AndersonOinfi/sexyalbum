package com.sexyalbum.sexyweb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {
    // get userinfo for current user
    @RequestMapping()
    public String getUserInfo(){
        //todo
        return "user";
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
    public String signupUserAccount(){
        return null;
    }

    // user password management
    @RequestMapping(value = "/password/change")
    public String changeUserPassword(){
        return null;
    }
}
