package com.eleven.icode.controller;

import com.eleven.icode.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private DemoService demoService;

    @RequestMapping(value = "/consumer/user/say")
    public String sayHello() {
        String result = demoService.sayHello("elevenController");
        return result;
    }
}
