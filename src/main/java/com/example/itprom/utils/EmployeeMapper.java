package com.example.itprom.utils;

import com.example.itprom.domain.Department;
import com.example.itprom.domain.Employee;
import com.example.itprom.domain.Profession;
import com.example.itprom.dto.EmployeeDto;

public final class EmployeeMapper {

    public static EmployeeDto map(Employee employee) {
        EmployeeDto result = new EmployeeDto();
        result.setId(employee.getId());
        result.setFirstName(employee.getFirstName());
        result.setSecondName(employee.getSecondName());
        result.setPatronymic(employee.getPatronymic());
        result.setNote(employee.getNote());

        Department department = employee.getDepartment();
        if (department != null) {
            result.setDepartmentName(department.getName());
        }

        Profession profession = employee.getProfession();
        if (profession != null) {
            result.setProfessionName(profession.getName());
        }

        return result;
    }

    public static Employee map(EmployeeDto employeeDto) {
        Employee result = new Employee();
        result.setId(employeeDto.getId());
        result.setFirstName(employeeDto.getFirstName());
        result.setSecondName(employeeDto.getSecondName());
        result.setPatronymic(employeeDto.getPatronymic());
        result.setNote(employeeDto.getNote());

        return result;
    }
}
