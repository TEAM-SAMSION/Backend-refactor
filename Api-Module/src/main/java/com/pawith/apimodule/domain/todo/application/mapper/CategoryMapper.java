package com.pawith.apimodule.domain.todo.application.mapper;

import com.pawith.apimodule.domain.todo.application.dto.request.CategoryCreateRequest;
import com.pawith.tododomain.entity.Category;
import com.pawith.tododomain.entity.CategoryStatus;
import com.pawith.tododomain.entity.TodoTeam;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryMapper {

    public static Category mapToCategoryEntity(TodoTeam todoTeam, CategoryCreateRequest request){
        return Category.builder()
            .name(request.getCategoryName())
            .categoryStatus(CategoryStatus.ON)
            .todoTeam(todoTeam)
            .build();
    }
}
