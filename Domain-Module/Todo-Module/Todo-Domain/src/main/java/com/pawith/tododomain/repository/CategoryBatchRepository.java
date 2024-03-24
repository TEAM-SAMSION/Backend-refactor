package com.pawith.tododomain.repository;

import com.pawith.tododomain.entity.Category;

import java.util.Collection;

public interface CategoryBatchRepository {
    void saveAllBatch(Collection<Category> entities);
}
