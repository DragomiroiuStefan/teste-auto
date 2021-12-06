package com.stefan.testeauto.repository;

import com.stefan.testeauto.dto.UserTestAnswersDto;
import com.stefan.testeauto.dto.UserTestDto;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.stefan.model.Tables.*;
import static org.jooq.impl.DSL.count;

@Repository
public class UserRepository {

    @Autowired
    DSLContext context;

    public List<UserTestDto> getUserTests(int userId) {
        return context.select(
                    USER_TESTS.USER_TEST_ID,
                    USER_TESTS.CATEGORY_ID,
                    USER_TESTS.END_TIME,
                    USER_TESTS.TIME_SPENT,
                    count(USER_TEST_ANSWERS).as("answers"),
                    CATEGORY.NO_OF_QUESTIONS.as("total")
                )
                .from(USER_TESTS)
                .innerJoin(USER_TEST_ANSWERS).on(USER_TEST_ANSWERS.USER_TEST_ID.eq(USER_TESTS.USER_TEST_ID))
                .innerJoin(CATEGORY).on(CATEGORY.CATEGORY_ID.eq(USER_TESTS.CATEGORY_ID))
                .where(USER_TESTS.USER_ID.eq(userId))
                .groupBy(USER_TESTS.USER_TEST_ID, CATEGORY.CATEGORY_ID)
                .fetchInto(UserTestDto.class);
    }

    public List<UserTestAnswersDto> getUserTestAnswers(Integer testId) {
        return context.select()
                .from(USER_TEST_ANSWERS)
                .innerJoin(QUESTIONS).on(QUESTIONS.QUESTION_ID.eq(USER_TEST_ANSWERS.QUESTION_ID))
                .where(USER_TEST_ANSWERS.USER_TEST_ID.eq(testId))
                .fetchInto(UserTestAnswersDto.class);
    }

}
