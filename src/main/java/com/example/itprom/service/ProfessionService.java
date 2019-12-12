package com.example.itprom.service;

import com.example.itprom.domain.Profession;
import com.example.itprom.dto.ProfessionDto;

import java.util.List;
import java.util.Optional;

public interface ProfessionService {

    List<ProfessionDto> getAllProfession();

    Optional<Profession> getByName(String name);

    ProfessionDto getProfessionById(Long id);

    Profession update(ProfessionDto professionDto);

    void delete(Long id);

    List<String> geProfessionsName();
}
