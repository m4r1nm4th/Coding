package com.example.santa.controller;

import com.example.santa.model.User;
import com.example.santa.service.SecretSantaService;
import com.example.santa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/santa")
public class UserController {

    @Autowired
    private SecretSantaService secretSantaService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return secretSantaService.registerUser(user);
    }

    @PostMapping("/assign")
    public String assignSecretSantas() {
        secretSantaService.assignSecretSantas();
        return "Secret Santa assignments completed!";
    }

    @GetMapping("/{userId}/assigned")
    public User getAssignedUser(@PathVariable Long userId) {
        return secretSantaService.getSecretSantaFor(userId);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
