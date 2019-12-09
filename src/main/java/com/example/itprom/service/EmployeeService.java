package com.example.itprom.service;

import com.example.itprom.domain.Employee;
import com.example.itprom.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDto> getAllEmployees();

    EmployeeDto getEmployeeById(Long id);

    Employee update(EmployeeDto employeeDto);

    void delete(Long id);
}
