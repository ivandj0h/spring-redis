package ivandjoh.online.springredis.controller;

import ivandjoh.online.springredis.entity.User;
import ivandjoh.online.springredis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("")
    public String get() {
        return "Hello World!";
    }

    @GetMapping("/users")
    @Cacheable(value = "users", key = "#userId", unless = "#result.followers < 12000")
    public ResponseEntity<?> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{userId}")
    @Cacheable(value = "users", key = "#userId", unless = "#result.followers < 12000")
    public ResponseEntity<?> getUser(@PathVariable String userId) {
        return userService.getUser(userId);
    }

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        return userService.createUser();
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable String userId, @RequestBody User user) {
        return userService.updateUser(userId, user);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        return userService.deleteUser(userId);
    }
}
