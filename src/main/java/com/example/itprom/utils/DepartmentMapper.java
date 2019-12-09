package com.example.itprom.utils;

import com.example.itprom.domain.Department;
import com.example.itprom.dto.DepartmentDto;

public final class DepartmentMapper {

    public static DepartmentDto map(Department department) {
        DepartmentDto result = new DepartmentDto();
        result.setId(department.getId());
        result.setName(department.getName());
        result.setNote(department.getNote());

        Department parent = department.getParentDepartment();
        if (parent != null) {
            result.setParentDepartmentName(parent.getName());
        }

        return result;
    }

    public static Department map(DepartmentDto departmentDto) {
        Department department = new Department();
        department.setId(departmentDto.getId());
        department.setName(departmentDto.getName());
        department.setNote(departmentDto.getNote());

        return department;
    }
}
