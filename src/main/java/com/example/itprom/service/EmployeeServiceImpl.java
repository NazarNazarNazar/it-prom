package com.example.itprom.service;

import com.example.itprom.domain.Employee;
import com.example.itprom.dto.EmployeeDto;
import com.example.itprom.exception.EmployeeNotFoundException;
import com.example.itprom.repository.EmployeeRepository;
import com.example.itprom.utils.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;
    private final ProfessionService professionService;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               DepartmentService departmentService,
                               ProfessionService professionService) {
        this.employeeRepository = employeeRepository;
        this.departmentService = departmentService;
        this.professionService = professionService;
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .filter(Objects::nonNull)
                .map(EmployeeMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        return EmployeeMapper.map(employeeRepository.findById(id).orElseThrow(() ->
                new EmployeeNotFoundException(String.format("Employee with id: %d not found", id))));
    }

    @Override
    public Employee update(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.map(employeeDto);

        String departmentName = employeeDto.getDepartmentName();
        if (departmentName != null) {
            departmentService.getByName(departmentName)
                    .ifPresent(employee::setDepartment);
        }

        String professionName = employeeDto.getProfessionName();
        if (professionName != null) {
            professionService.getByName(professionName)
                    .ifPresent(employee::setProfession);
        }

        return employeeRepository.save(employee);
    }

    @Override
    public void delete(Long id) {
        employeeRepository.delete(employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(String.format("Employee with id: %d not found", id))));
    }
}
