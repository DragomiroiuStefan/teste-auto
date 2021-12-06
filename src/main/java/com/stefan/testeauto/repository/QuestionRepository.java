package com.stefan.testeauto.repository;

import com.stefan.model.tables.pojos.Questions;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.stefan.model.Tables.QUESTIONS;

@Repository
public class QuestionRepository {

    @Autowired
    DSLContext context;

    public List<Questions> getTest(String category, Short noOfQuestions) {
        var testQuery = """
                select * from questions q
                inner join category_questions cq on q.question_id =cq.question_id
                where cq.category_id = '%s'
                order by random() limit %d;
                """.formatted(category, noOfQuestions);
        Result<Record> testRecords = context.fetch(testQuery);
        List<Questions> test = new ArrayList<>();
        for (Record record : testRecords) {
            test.add(new Questions(
                    record.getValue(QUESTIONS.QUESTION_ID),
                    record.getValue(QUESTIONS.TEXT),
                    record.getValue(QUESTIONS.FIGURE),
                    record.getValue(QUESTIONS.ANSWERS),
                    record.getValue(QUESTIONS.CORRECT_ANSWERS)
            ));
        }
        return test;
    }

}
