package com.example.itprom.repository;

import com.example.itprom.domain.Profession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfessionRepository extends JpaRepository<Profession, Long> {

    Profession findFirstByName(String name);

    @Query("SELECT p.name FROM Profession p")
    List<String> getAllNames();
}
