package com.example.demo.adapters;

import com.example.demo.adapters.model.User;
import com.example.demo.adapters.repositories.UserRepository;
import io.vavr.collection.Seq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public void createNewUser(User user) {
        userRepository.saveAndFlush(user);
    }

    public Seq<User> getAllUsers() {
        return userRepository.findAll();
    }
}
