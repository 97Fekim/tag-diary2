package com.fekim.tagdiary2.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping("/")
public class TestController {

    @GetMapping("/blog-post-template/index")
    public void gotoIndex(){

    }

    @GetMapping("/blog-post-template/contact")
    public void gotoContact(){

    }

    @GetMapping("/blog-post-template/about")
    public void gotoAbout(){

    }

    @GetMapping("/blog-post-template/post")
    public void gotoPost(){

    }

    @GetMapping("/auth/signIn")
    public void gotoSignIn(){

    }

    @GetMapping("/signInModal/index")
    public void sogoSignInModal(){

    }

}
