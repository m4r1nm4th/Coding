package com.example.santa.service;

import com.example.santa.model.User;
import com.example.santa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SecretSantaService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public void assignSecretSantas() {
        List<User> users = userRepository.findAll();
        Collections.shuffle(users); // Randomize user list for assignment

        // Circular assignment of Secret Santas
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            User secretSantaFor = users.get((i + 1) % users.size());
            user.setSecretSantaFor(secretSantaFor.getId());
            userRepository.save(user);
        }
    }

    public User getSecretSantaFor(Long userId) {
        return userRepository.findById(userId)
                .map(user -> userRepository.findById(user.getSecretSantaFor()).orElse(null))
                .orElse(null);
    }
}
