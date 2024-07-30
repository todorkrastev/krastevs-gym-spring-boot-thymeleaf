package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.exception.ResourceNotFoundException;
import com.todorkrastev.krastevsgym.model.dto.EmployeeDetailsDTO;
import com.todorkrastev.krastevsgym.model.dto.EmployeesShortInfoDTO;
import com.todorkrastev.krastevsgym.repository.EmployeeRepository;
import com.todorkrastev.krastevsgym.service.EmployeesService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeesServiceImpl implements EmployeesService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeesServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<EmployeesShortInfoDTO> findAll() {
        return employeeRepository.findAll()
                .stream()
                .map(employee -> modelMapper.map(employee, EmployeesShortInfoDTO.class))
                .toList();
    }

    @Override
    public EmployeeDetailsDTO findById(Long id) {
        return employeeRepository
                .findById(id).map(employee -> modelMapper.map(employee, EmployeeDetailsDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
    }
}
