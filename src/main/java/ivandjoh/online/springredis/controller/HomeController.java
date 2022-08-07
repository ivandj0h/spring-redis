package ivandjoh.online.springredis.controller;

import ivandjoh.online.springredis.entity.User;
import ivandjoh.online.springredis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class HomeController {

    @Autowired
    UserService userService;


    @GetMapping("/")
    public String home() {
        return "Hello World!";
    }

    @GetMapping("/users/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/user/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return userService.addUser(user);
    }
}
