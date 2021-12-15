package com.stefan.testeauto.repository;

import com.stefan.model.tables.records.UserTestAnswersRecord;
import com.stefan.model.tables.records.UserTestsRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import static com.stefan.model.Tables.USER_TESTS;
import static com.stefan.model.Tables.USER_TEST_ANSWERS;

@Repository
public class TestRepository {

    @Autowired
    DSLContext context;

    public int saveTest(Integer userId, String category, String timeSpent) {
        var testsRecord = new UserTestsRecord();
        testsRecord.setUserId(userId);
        testsRecord.setCategoryId(category);
        testsRecord.setEndTime(LocalDateTime.now());
        testsRecord.setTimeSpent(timeSpent);
        return context.insertInto(USER_TESTS)
                .set(testsRecord)
                .returning(USER_TESTS.USER_TEST_ID)
                .fetchOne()
                .getUserTestId();
    }

    public void saveTestAnswers(Integer testId, Integer questionId, Short[] userAnswers) {
        var testAnswersRecord = new UserTestAnswersRecord();
        testAnswersRecord.setUserTestId(testId);
        testAnswersRecord.setQuestionId(questionId);
        testAnswersRecord.setUserAnswers(userAnswers);
        context.insertInto(USER_TEST_ANSWERS)
                .set(testAnswersRecord)
                .execute();
    }

}
