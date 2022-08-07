package ivandjoh.online.springredis.service.impl;

import ivandjoh.online.springredis.entity.User;
import ivandjoh.online.springredis.exception.UserNotFoundException;
import ivandjoh.online.springredis.repository.UserRepository;
import ivandjoh.online.springredis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Cacheable(value = "users", key = "#id", condition = "#id != null")
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
    public User addUser(User user) {
        try {
            if (user == null) {
                throw new UserNotFoundException("User not found!");
            } else {
                User newUser = userRepository.save(user);
                return newUser;
            }
        } catch (Exception e) {
            throw new UserNotFoundException("Server Error!");
        }
    }

    @Override
    @CachePut(value = "User", key = "#id", condition = "#id != null")
    public User updateUser(Long id, User user) {
        try {
            if (user == null) {
                throw new UserNotFoundException("User not found!");
            } else {
                User activeUser = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User not found!"));
                activeUser.setUserName(user.getUserName());
                activeUser.setUserEmail(user.getUserEmail());
                activeUser.setPhoneNumber(user.getPhoneNumber());

                return userRepository.save(activeUser);
            }
        } catch (Exception e) {
            throw new UserNotFoundException("Server Error!");
        }
    }

    @Override
    @Cacheable(value = "User", key = "#id", condition = "#id != null")
    public User getUserById(Long id) {
        try {
            User user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User not found!"));
            return user;
        } catch (Exception e) {
            throw new UserNotFoundException("Server Error!");
        }
    }

    @Override
    @CacheEvict(value = "User", key = "#id")
    public void deleteUser(Long id) {
        try {
            User user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User not found!"));
            userRepository.delete(user);
        } catch (Exception e) {
            throw new UserNotFoundException("Server Error!");
        }
    }
}
