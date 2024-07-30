package com.todorkrastev.krastevsgym.service;

import com.todorkrastev.krastevsgym.model.dto.EmployeeDetailsDTO;
import com.todorkrastev.krastevsgym.model.dto.EmployeesShortInfoDTO;

import java.util.List;

public interface EmployeesService {
    List<EmployeesShortInfoDTO> findAll();

    EmployeeDetailsDTO findById(Long id);
}
