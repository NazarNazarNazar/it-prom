package com.example.itprom;

import com.example.itprom.domain.Department;
import com.example.itprom.domain.Employee;
import com.example.itprom.domain.Profession;
import com.example.itprom.dto.EmployeeDto;
import com.example.itprom.utils.EmployeeMapper;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class TestData {

    public static List<EmployeeDto> employeeDtoList() {
        return Arrays.asList(employeeDto(), employeeDto(), employeeDto());
    }

    public static List<Employee> employeeList() {
        return Arrays.asList(employee(), employee(), employee(), employee());
    }

    public static EmployeeDto employeeDto() {
        return EmployeeMapper.map(employee());
    }

    public static Employee employee() {
        Employee result = new Employee();
        result.setId(id());
        result.setFirstName(String.format("firstName-%s", randomString()));
        result.setSecondName(String.format("secondName-%s", randomString()));
        result.setPatronymic(String.format("patronymic-%s", randomString()));
        result.setNote(String.format("note-%s", randomString()));
        result.setProfession(profession());
        result.setDepartment(department());

        return result;
    }

    public static Profession profession() {
        Profession result = new Profession();
        result.setId(id());
        result.setName(name());
        result.setNote(note());

        return result;
    }

    public static Department parentDepartment() {
        Department result = new Department();
        result.setId(id());
        result.setName(name());
        result.setNote(note());
        result.setParentDepartment(null);

        return result;
    }

    public static Department department() {
        Department result = new Department();
        result.setId(id());
        result.setName(name());
        result.setNote(note());
        result.setParentDepartment(parentDepartment());

        return result;
    }

    private static Long id() {
        return new SecureRandom().nextLong() & Long.MAX_VALUE;
    }

    private static String name() {
        return String.format("name-%s", randomString());
    }

    private static String note() {
        return String.format("note-%s", randomString());
    }

    private static String randomString() {
        return UUID.randomUUID().toString();
    }
}
