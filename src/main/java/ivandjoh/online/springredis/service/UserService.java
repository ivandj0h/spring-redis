package ivandjoh.online.springredis.service;

import ivandjoh.online.springredis.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<List<User>> getAllUsers();

    ResponseEntity<User> addUser(User user);

    User updateUser(Long id, User user);
}
