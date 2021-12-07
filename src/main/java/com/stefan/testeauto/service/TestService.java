package com.stefan.testeauto.service;

import com.stefan.model.tables.pojos.Category;
import com.stefan.model.tables.pojos.Questions;
import com.stefan.testeauto.dto.UserTestRequest;
import com.stefan.testeauto.repository.CategoryRepository;
import com.stefan.testeauto.repository.QuestionRepository;
import com.stefan.testeauto.repository.TestRepository;
import com.stefan.testeauto.util.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TestService {

    private final TestRepository testRepository;
    private final CategoryRepository categoryRepository;
    private final QuestionRepository questionRepository;

    public TestService(TestRepository testRepository, CategoryRepository categoryRepository, QuestionRepository questionRepository) {
        this.testRepository = testRepository;
        this.categoryRepository = categoryRepository;
        this.questionRepository = questionRepository;
    }

    @Transactional
    public List<Questions> getTest(String categoryId) {
        Category category = categoryRepository.getCategory(categoryId);
        return questionRepository.getTest(categoryId, category.getNoOfQuestions());
    }

    @Transactional
    public void saveTest(UserTestRequest userTestRequest) {
        int testId = testRepository.saveTest(
                userTestRequest.getUserId(),
                userTestRequest.getCategory(),
                userTestRequest.getTimeSpent()
        );
        for (var testAnswer: userTestRequest.getTestAnswers()){
            testRepository.saveTestAnswers(
                    testId,
                    testAnswer.getQuestionId(),
                    testAnswer.getUserAnswers()
            );
        }
    }
}
