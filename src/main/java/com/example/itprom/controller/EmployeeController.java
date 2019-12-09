package com.example.itprom.controller;

import com.example.itprom.dto.EmployeeDto;
import com.example.itprom.exception.EmployeeNotFoundException;
import com.example.itprom.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
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

// TODO: create parent abstract controller
@RestController
@RequestMapping(value = "/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    private ResponseEntity<?> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> getEmployeeById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(employeeService.getEmployeeById(id));
        } catch (EmployeeNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }

    // TODO: make update and create methods with appropriate status response
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<?> update(@Valid @RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.ok(employeeService.update(employeeDto));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            employeeService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EmployeeNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }
}
