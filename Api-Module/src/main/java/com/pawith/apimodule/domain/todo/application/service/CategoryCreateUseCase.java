package com.pawith.apimodule.domain.todo.application.service;

import com.pawith.apimodule.domain.todo.application.dto.request.CategoryCreateRequest;
import com.pawith.apimodule.domain.todo.application.mapper.CategoryMapper;
import com.pawith.commonmodule.annotation.ApplicationService;
import com.pawith.tododomain.entity.Category;
import com.pawith.tododomain.entity.TodoTeam;
import com.pawith.tododomain.service.CategorySaveService;
import com.pawith.tododomain.service.TodoTeamQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@ApplicationService
@RequiredArgsConstructor
@Transactional
public class CategoryCreateUseCase {
    private final CategorySaveService categorySaveService;
    private final TodoTeamQueryService todoTeamQueryService;

    public void createCategory(Long todoTeamId, CategoryCreateRequest request) {
        TodoTeam todoTeam = todoTeamQueryService.findTodoTeamById(todoTeamId);
        Category category = CategoryMapper.mapToCategoryEntity(todoTeam, request);
        categorySaveService.saveCategory(category);
    }

}
