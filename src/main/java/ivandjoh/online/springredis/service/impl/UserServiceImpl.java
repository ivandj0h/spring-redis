package ivandjoh.online.springredis.service.impl;

import ivandjoh.online.springredis.entity.User;
import ivandjoh.online.springredis.exception.UserNotFoundException;
import ivandjoh.online.springredis.repository.UserRepository;
import ivandjoh.online.springredis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseEntity<List<User>> getAllUsers() {

        try {
            List<User> users = userRepository.findAll();
            if (users.isEmpty()) {
                throw new UserNotFoundException("Users not found");
            } else {
                return new ResponseEntity<>(users, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<User> addUser(User user) {
        try {
            if (user == null) {
                throw new UserNotFoundException("User not found!");
            } else {
                User newUser = userRepository.save(user);
                return new ResponseEntity<>(newUser, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
