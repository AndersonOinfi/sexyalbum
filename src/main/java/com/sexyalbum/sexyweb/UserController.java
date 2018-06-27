package com.sexyalbum.sexyweb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public String UserInfo(@RequestParam String username){
        //todo
        return "user";
    }
}
