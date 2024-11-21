package com.zohu.weatherapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FaviconController {

    @RequestMapping("/favicon.ico")
    @ResponseBody
    void handleFavicon() {
        // No response or a simple one
    }
}