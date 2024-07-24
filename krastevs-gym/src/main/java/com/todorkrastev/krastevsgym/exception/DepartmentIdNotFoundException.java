package com.todorkrastev.krastevsgym.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class DepartmentIdNotFoundException extends RuntimeException {
    private final Long departmentId;

    public DepartmentIdNotFoundException(Long departmentId) {
        super(String.format("Product not found with department id: '%d'", departmentId));
        this.departmentId = departmentId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }
}
