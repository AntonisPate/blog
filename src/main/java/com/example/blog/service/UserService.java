package com.example.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.model.User;
import com.example.blog.repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    /**
     * Creates a new user
     * @param user the user for creating
     * @return User the created user
     */
    public HashMap<String, String> createUser(User user) {

        User checkUsers = userRepository.findByEmail(user.getEmail());

        if (checkUsers != null) {
            HashMap<String, String> map = new HashMap<>();
            map.put("status", "false");
            map.put("message", "User with this email already exists!");
            return map;
        } else {
            userRepository.save(user);
            HashMap<String, String> map = new HashMap<>();
            map.put("status", "true");
            return map;
        }

    }

    /**
     * Returns a list with all users
     * @return List<User> all the users
     */
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    /**
     * Returns single user based on id
     * @param id the id of the user
     * @return User the searched user
     */
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    /**
     * Deletes a user based on the id
     * @param id the id of the user
     */
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * Updates a user based on the id and the body data
     * @param id the id of the user
     * @param userDetails the posted data
     * @return User the updated user
     */
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).get();
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setRole(userDetails.getRole());

        return userRepository.save(user);
    }

    public Object login(User userDetails) {
        String email = userDetails.getEmail();
        User user = userRepository.findByEmail(email);

        if (user != null) {
            String password = userDetails.getPassword();

            if (password.equals(user.getPassword())) {
                return generateToken(user);
            }
        }

        return false;
    }

    private int generateToken(User user) {
        int min = 100000;
        int max = 999999;
        int token = (int)Math.floor(Math.random()*(max-min+1)+min);
        user.setToken(token);
        userRepository.save(user);
        return token;
    }
}
