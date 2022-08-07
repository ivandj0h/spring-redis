package ivandjoh.online.springredis.service.impl;

import ivandjoh.online.springredis.entity.User;
import ivandjoh.online.springredis.repository.UserRepository;
import ivandjoh.online.springredis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseEntity<?> getAllUsers() {

        List<User> users = userRepository.findAll();

        return users.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(users);
    }
}
