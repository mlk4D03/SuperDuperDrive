package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.Model.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {

    private UserMapper userMapper;

    private HashService hashService;

    public UserService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    public boolean isUsernameAvailable(String username){
        return this.userMapper.getUser(username) == null;
    }

    public void addUser(User user){
        this.userMapper.insertUser(user);
    }

    public User getUser(String username){
        return this.userMapper.getUser(username);
    }

    public Integer getUserId(String username){
        return this.userMapper.getUserId(username);
    }

    public int createUser(User user){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = this.hashService.getHashedValue(user.getPassword(),encodedSalt);
        return userMapper.insertUser(new User(null,user.getUsername(),encodedSalt,hashedPassword,user.getFirstname(),user.getLastname()));
    }
}
