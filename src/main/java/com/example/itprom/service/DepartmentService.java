package com.example.itprom.service;

import com.example.itprom.domain.Department;
import com.example.itprom.dto.DepartmentDto;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    List<DepartmentDto> getAllDepartments();

    Optional<Department> getByName(String name);

    DepartmentDto getById(Long id);

    Department update(DepartmentDto departmentDto);

    void delete(Long id);

    List<String> getDepartmentsName();
}
