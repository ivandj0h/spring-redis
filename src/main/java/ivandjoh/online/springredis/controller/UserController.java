package ivandjoh.online.springredis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @GetMapping("")
    public String get() {
        return "Hello World!";
    }

    @GetMapping("/users")
    public String getUser() {
        return "User";
    }
}
