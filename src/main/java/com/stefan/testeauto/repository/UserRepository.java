package com.stefan.testeauto.repository;

import com.stefan.model.tables.pojos.Users;
import com.stefan.model.tables.records.UsersRecord;
import com.stefan.testeauto.dto.UserTestAnswersDto;
import com.stefan.testeauto.dto.UserTestDto;
import org.jooq.DSLContext;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.stefan.model.Tables.*;
import static org.jooq.impl.DSL.count;

@Repository
public class UserRepository {

    @Autowired
    DSLContext context;

    @Autowired
    private ModelMapper modelMapper;

    public Optional<Users> getUserByEmail(String email) {
        return context.selectFrom(USERS)
                .where(USERS.EMAIL.eq(email))
                .fetchOptionalInto(Users.class);
    }

    public Users save(Users user) {
        var userRecord = new UsersRecord();
        userRecord.setFirstName(user.getFirstName());
        userRecord.setLastName(user.getLastName());
        userRecord.setEmail(user.getEmail());
        userRecord.setPassword(user.getPassword());

        UsersRecord returnedUsersRecord = context.insertInto(USERS)
                .set(userRecord)
                .returning()
                .fetchOne();

        return modelMapper.map(returnedUsersRecord, Users.class);
    }

    public void updateLastLogin(String email) {
        context.update(USERS)
                .set(USERS.LAST_LOGIN, LocalDateTime.now())
                .where(USERS.EMAIL.eq(email))
                .execute();
    }

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
