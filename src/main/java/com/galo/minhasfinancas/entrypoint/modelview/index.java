package com.galo.minhasfinancas.entrypoint.modelview;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class index {

    @RequestMapping("/")
    public String home(){
        return "home";
    }

    @RequestMapping("/saibamais")
    public String saibaMais(){
        return "free/saibamais";
    }

    @RequestMapping("/advanced")
    public String advanced(){
        return "free/advanced";
    }
}
