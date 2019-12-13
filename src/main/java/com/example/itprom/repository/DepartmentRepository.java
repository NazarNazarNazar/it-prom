package com.example.itprom.repository;

import com.example.itprom.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findFirstByName(String name);

    @Query("SELECT d.name FROM Department d")
    List<String> getAllNames();
}
