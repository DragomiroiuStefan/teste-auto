package com.stefan.testeauto.controller;

import com.stefan.model.tables.pojos.Users;
import com.stefan.testeauto.dto.UserTestAnswersDto;
import com.stefan.testeauto.dto.UserTestDto;
import com.stefan.testeauto.repository.UserRepository;
import com.stefan.testeauto.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    private final UserRepository userRepository;
    private final AuthService authService;

    public UserController(UserRepository userRepository, AuthService authService) {
        this.userRepository = userRepository;
        this.authService = authService;
    }

    @PostMapping("/users/login")
    public Users login(@RequestBody Users user, HttpServletResponse response) {
        Optional<Users> userRecord = userRepository.getUserByEmail(user.getEmail());
        if (userRecord.isEmpty() ||  !user.getPassword().equals(userRecord.get().getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "bad credentials");
        }
        userRepository.updateLastLogin(user.getEmail());
        String jwt = authService.createJWT(user.getEmail());
        response.addHeader("Authorization", "Bearer: " + jwt);
        response.addHeader("Access-Control-Expose-Headers", "Authorization");
        return userRecord.get();
    }

    @PostMapping("/users/register")
    public Users register(@RequestBody Users user, HttpServletResponse response) {
        String jwt = authService.createJWT(user.getEmail());
        response.addHeader("Authorization", "Bearer: " + jwt);
        response.addHeader("Access-Control-Expose-Headers", "Authorization");
        return userRepository.save(user);
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
