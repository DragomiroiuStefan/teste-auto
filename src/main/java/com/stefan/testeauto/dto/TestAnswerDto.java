package com.stefan.testeauto.dto;

import java.util.List;

public class TestAnswerDto {

    private Integer questionId;
    private Short[] userAnswers = new Short[3];

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Short[] getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(Short[] userAnswers) {
        this.userAnswers = userAnswers;
    }
}
