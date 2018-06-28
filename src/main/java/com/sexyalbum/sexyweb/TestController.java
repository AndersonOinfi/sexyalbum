package com.sexyalbum.sexyweb;

import com.sexyalbum.jdbc.EleDao;
import com.sexyalbum.jdbc.UserDao;
import com.sexyalbum.jdbc.UserDaoImpl;
import com.sexyalbum.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private EleDao eleDao;

    @RequestMapping(value = "/adduser", method = RequestMethod.GET)
    public String testAddUser(@RequestParam String username,
                              @RequestParam String password){
        return String.valueOf(userDao.add(new User(username,password)));
    }

    @RequestMapping(value = "/getuser", method = RequestMethod.GET)
    public String testGetUser(@RequestParam String username){
        return (userDao.find(username)).toString();
    }

    @RequestMapping(value = "/updateuser", method = RequestMethod.GET)
    public String testUpdateUser(@RequestParam String username,
                                 @RequestParam String password){
        return String.valueOf(userDao.update(new User(username,password)));
    }

    @RequestMapping(value = "/getele", method = RequestMethod.GET)
    public String testGetEle(@RequestParam Long eleid){
        return (eleDao.find(eleid)).toString();
    }
}
