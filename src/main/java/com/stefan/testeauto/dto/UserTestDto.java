package com.stefan.testeauto.dto;

import org.jooq.types.YearToSecond;

import java.time.LocalDateTime;

public class UserTestDto {

    private Integer userTestId;
    private String categoryId;
    private LocalDateTime endTime;
    private YearToSecond timeSpent;
    private Integer answers;
    private Short total;

    public Integer getUserTestId() {
        return userTestId;
    }

    public void setUserTestId(Integer userTestId) {
        this.userTestId = userTestId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public YearToSecond getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(YearToSecond timeSpent) {
        this.timeSpent = timeSpent;
    }

    public Integer getAnswers() {
        return answers;
    }

    public void setAnswers(Integer answers) {
        this.answers = answers;
    }

    public Short getTotal() {
        return total;
    }

    public void setTotal(Short total) {
        this.total = total;
    }
}
