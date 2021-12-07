package com.stefan.testeauto.repository;

import com.stefan.model.tables.pojos.Category;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.stefan.model.Tables.CATEGORY;

@Repository
public class CategoryRepository {

    @Autowired
    DSLContext context;

    public Category getCategory(String categoryId) {
        return context. selectFrom(CATEGORY)
                .where(CATEGORY.CATEGORY_ID.eq(categoryId))
                .fetchOneInto(Category.class);
    }

    public List<Category> getCategories() {
        return context. selectFrom(CATEGORY)
                .fetchInto(Category.class);
    }

}
