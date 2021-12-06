package com.stefan.testeauto.dto;

import java.time.LocalDateTime;
import java.util.List;

public class UserTestRequest {

    private Integer userId;
    private String category;
    private LocalDateTime endTime;
    private String timeSpent;
    private List<TestAnswerDto> testAnswers;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(String timeSpent) {
        this.timeSpent = timeSpent;
    }

    public List<TestAnswerDto> getTestAnswers() {
        return testAnswers;
    }

    public void setTestAnswers(List<TestAnswerDto> testAnswers) {
        this.testAnswers = testAnswers;
    }
}
