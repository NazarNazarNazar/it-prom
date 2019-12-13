package com.example.itprom.controller;

import com.example.itprom.dto.DepartmentDto;
import com.example.itprom.exception.DepartmentNotFoundException;
import com.example.itprom.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    private ResponseEntity<?> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @GetMapping("/{id}")
    private ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(departmentService.getById(id));
        } catch (DepartmentNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<?> update(@Valid @RequestBody DepartmentDto departmentDto) {
        return ResponseEntity.ok(departmentService.update(departmentDto));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            departmentService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (DepartmentNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/name")
    private ResponseEntity<?> getDepartmentsName() {
        return ResponseEntity.ok(departmentService.getDepartmentsName());
    }
}
