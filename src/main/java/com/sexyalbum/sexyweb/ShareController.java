package com.sexyalbum.sexyweb;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/share")
public class ShareController {
    @RequestMapping(value = "")
    public String share(){
        return null;
    }
}
