package com.stefan.testeauto.dto;

public class UserTestAnswersDto {

    private Integer  questionId;
    private String   text;
    private byte[]   figure;
    private String[] answers;
    private Short[]  correctAnswers;
    private Short[] userAnswers;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public byte[] getFigure() {
        return figure;
    }

    public void setFigure(byte[] figure) {
        this.figure = figure;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public Short[] getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(Short[] correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public Short[] getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(Short[] userAnswers) {
        this.userAnswers = userAnswers;
    }
}
