package com.todorkrastev.krastevsgym.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ProductByCategoryIdAndDepartmentIdNotFoundException extends RuntimeException{
    private Long categoryId;
    private Long departmentId;

    public ProductByCategoryIdAndDepartmentIdNotFoundException(Long categoryId, Long departmentId) {
        super(String.format("Product not found with category id %d and department id %d", categoryId, departmentId));
        this.categoryId = categoryId;
        this.departmentId = departmentId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }
}
