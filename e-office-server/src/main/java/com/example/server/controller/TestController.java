package com.example.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dengyinshan
 * @version 1.0.0
 * @createTime 2021/11/1 20:05
 * @Description 测试接口
 */
@RestController
public class TestController {

    @GetMapping("/api/hello")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/employee/basic/hello")
    public String hello2() {
        return "Hello my employee!";
    }

    @GetMapping("/employee/advanced/hello")
    public String hello3() {
        return "/employee/advanced/hello!";
    }
}
