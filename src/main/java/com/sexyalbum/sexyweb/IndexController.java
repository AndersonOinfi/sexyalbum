package com.sexyalbum.sexyweb;

import com.sexyalbum.model.Album;
import com.sexyalbum.model.Ele;
import com.sexyalbum.model.User;
import com.sexyalbum.service.AlbumService;
import com.sexyalbum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class IndexController {
    @Autowired
    private UserService userService;
    @Autowired
    private AlbumService albumService;

    private static int length=1;

    @RequestMapping(value = "/hello")
    public String Hello(){
        return "hello";
    }

    @RequestMapping(value = "")
    public List<Ele> index(@RequestParam(name = "offset", defaultValue = "0") int offset,
                           @SessionAttribute(name = "currentuser") User currentuser){
        // todo User[] users=;
        List<Ele> eles=albumService.getUserEles(currentuser.getUserid());
        return eles.subList(offset,length);
    }
}
