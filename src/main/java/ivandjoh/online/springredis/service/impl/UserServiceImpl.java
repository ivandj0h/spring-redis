package ivandjoh.online.springredis.service.impl;

import ivandjoh.online.springredis.entity.User;
import ivandjoh.online.springredis.repository.UserRepository;
import ivandjoh.online.springredis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final String REDIS_CACHE_VALUE = "users";

    @Autowired
    UserRepository userRepository;

    @Override
    @Cacheable(value = REDIS_CACHE_VALUE, key = "#userId", unless = "#result.followers < 12000")
    public ResponseEntity<?> getAllUsers() {

        List<User> users = userRepository.findAll();

        return users.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(users);
    }

    @Override
    @CachePut(value = REDIS_CACHE_VALUE, key = "#userEntity.userId")
    public ResponseEntity<?> createUser() {

        User user = new User();
        userRepository.save(user);

        return new ResponseEntity<>(user, ResponseEntity.ok().build().getStatusCode());
    }

    @Override
    @CacheEvict(value = REDIS_CACHE_VALUE, key = "#userId")
    public ResponseEntity<?> deleteUser(Long userId) {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        } else {
            userRepository.delete(user);
            return ResponseEntity.ok().build();
        }
    }

    @Override
    @Cacheable(value = REDIS_CACHE_VALUE, key = "#userId", unless = "#result.followers < 12000")
    public ResponseEntity<?> getUser(String userId) {
        User user = userRepository.findByUserId(Long.parseLong(userId));
        if (user == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(user);
        }
    }

    @Override
    @Cacheable(value = REDIS_CACHE_VALUE, key = "#userId")
    public ResponseEntity<?> updateUser(String userId, User user) {
        User userEntity = userRepository.findByUserId(Long.parseLong(userId));
        if (userEntity == null) {
            return ResponseEntity.notFound().build();
        } else {
            userEntity.setFullName(user.getFullName());
            userEntity.setAges(user.getAges());
            userEntity.setEmailAddress(user.getEmailAddress());
            userEntity.setSalary(user.getSalary());
            userRepository.save(userEntity);

            return ResponseEntity.ok(userEntity);
        }
    }
}
