package com.example.itprom.repository;

import com.example.itprom.domain.Profession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionRepository extends JpaRepository<Profession, Long> {

    Profession findFirstByName(String name);
}
