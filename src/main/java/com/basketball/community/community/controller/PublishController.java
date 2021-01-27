package com.basketball.community.community.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class PublishController {

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }
}
