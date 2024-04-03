package com.pawith.apimodule.domain.todo.application.dto.response;

import com.pawith.tododomain.entity.CategoryStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryManageInfoResponse {
    private Long categoryId;
    private String categoryName;
    private CategoryStatus categoryStatus;
}
