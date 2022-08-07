package ivandjoh.online.springredis.service;

import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> getAllUsers();
}
