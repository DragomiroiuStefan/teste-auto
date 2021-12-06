package com.stefan.testeauto.controller;

import com.stefan.model.tables.pojos.Questions;
import com.stefan.testeauto.dto.UserTestRequest;
import com.stefan.testeauto.repository.QuestionRepository;
import com.stefan.testeauto.service.TestService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class TestController {

    private final TestService testService;

    public TestController(QuestionRepository questionRepository, TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/tests")
    public List<Questions> getTest(@RequestParam(value = "category") String categoryId) {
        try {
            return testService.getTest(categoryId);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing category: " + categoryId);
        }
    }

    @PostMapping("/tests")
    public void saveUserTest(@RequestBody UserTestRequest userTestRequest) {
        testService.saveTest(userTestRequest);
    }

}
