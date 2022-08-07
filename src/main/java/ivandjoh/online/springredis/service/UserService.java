package ivandjoh.online.springredis.service;

import ivandjoh.online.springredis.entity.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> getAllUsers();

    ResponseEntity<?> createUser();

    ResponseEntity<?> deleteUser(Long userId);

    ResponseEntity<?> getUser(String userId);

    ResponseEntity<?> updateUser(String userId, User user);
}
