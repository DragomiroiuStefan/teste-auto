package com.stefan.testeauto.controller;

import com.stefan.testeauto.dto.UserTestAnswersDto;
import com.stefan.testeauto.dto.UserTestDto;
import com.stefan.testeauto.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserRepository userRepository;


    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users/{userId}/tests")
    public List<UserTestDto> getUserTests(@PathVariable Integer userId) {
        return userRepository.getUserTests(userId);
    }

    @GetMapping("/users/{userId}/tests/{testId}")
    public List<UserTestAnswersDto> getUserTestAnswers(@PathVariable Integer testId) {
        return userRepository.getUserTestAnswers(testId);
    }
}
