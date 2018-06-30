package com.sexyalbum.sexyweb;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/explore")
public class ExploreController {
    @RequestMapping(value = "")
    public String exlore(){
        return null;
    }
}
