package com.example.itprom.service;

import com.example.itprom.domain.Department;
import com.example.itprom.dto.DepartmentDto;
import com.example.itprom.exception.DepartmentNotFoundException;
import com.example.itprom.repository.DepartmentRepository;
import com.example.itprom.utils.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .filter(Objects::nonNull)
                .map(DepartmentMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Department> getByName(String name) {
        return Optional.ofNullable(departmentRepository.findFirstByName(name));
    }

    @Override
    public DepartmentDto getById(Long id) {
        return DepartmentMapper.map(departmentRepository.findById(id).orElseThrow(() ->
                new DepartmentNotFoundException(String.format("Department with id: %d not found", id))));
    }

    @Override
    public Department update(DepartmentDto departmentDto) {
        Department department = DepartmentMapper.map(departmentDto);

        String parentDepartmentName = departmentDto.getParentDepartmentName();
        if (parentDepartmentName != null) {
            getByName(parentDepartmentName)
                    .ifPresent(department::setParentDepartment);

        }

        return departmentRepository.save(department);
    }

    @Override
    public void delete(Long id) {
        departmentRepository.delete(departmentRepository.findById(id)
                .orElseThrow(() ->
                        new DepartmentNotFoundException(String.format("Department with id: %d not found", id))));
    }
}
