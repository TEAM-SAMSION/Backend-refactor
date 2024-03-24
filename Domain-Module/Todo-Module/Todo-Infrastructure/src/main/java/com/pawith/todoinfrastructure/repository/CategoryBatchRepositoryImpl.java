package com.pawith.todoinfrastructure.repository;

import com.pawith.tododomain.entity.Category;
import com.pawith.tododomain.repository.CategoryBatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class CategoryBatchRepositoryImpl implements CategoryBatchRepository {
    private final JdbcTemplate jdbcTemplate;
    @Override
    public void saveAllBatch(Collection<Category> entities) {
        final String insertSql = """
            INSERT INTO category (team_id, name, category_status, disabled_at, is_deleted,created_at, updated_at)
            VALUES (?, ?, ?, ?, false,now(), now())
            """;

        jdbcTemplate.batchUpdate(insertSql, entities, 1000, (ps, category) -> {
            ps.setLong(1, category.getTodoTeam().getId());
            ps.setString(2, category.getName());
            ps.setString(3, category.getCategoryStatus().toString());
            ps.setDate(4, Date.valueOf(category.getDisabledAt()));
        });
    }
}
