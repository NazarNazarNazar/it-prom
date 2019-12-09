package com.example.itprom.service;

import com.example.itprom.domain.Profession;
import com.example.itprom.dto.ProfessionDto;
import com.example.itprom.exception.ProfessionNotFoundException;
import com.example.itprom.repository.ProfessionRepository;
import com.example.itprom.utils.ProfessionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfessionServiceImpl implements ProfessionService {

    private final ProfessionRepository professionRepository;

    @Autowired
    public ProfessionServiceImpl(ProfessionRepository professionRepository) {
        this.professionRepository = professionRepository;
    }

    @Override
    public List<ProfessionDto> getAllProfession() {
        return professionRepository.findAll()
                .stream()
                .filter(Objects::nonNull)
                .map(ProfessionMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Profession> getByName(String name) {
        return Optional.ofNullable(professionRepository.findFirstByName(name));
    }

    @Override
    public ProfessionDto getProfessionById(Long id) {
        return ProfessionMapper.map(professionRepository.findById(id).orElseThrow(() ->
                new ProfessionNotFoundException(String.format("Profession with id: %d not found", id))));
    }

    @Override
    public Profession update(ProfessionDto professionDto) {
        return professionRepository.save(ProfessionMapper.map(professionDto));
    }

    @Override
    public void delete(Long id) {
        professionRepository.delete(professionRepository.findById(id)
                .orElseThrow(() ->
                        new ProfessionNotFoundException(String.format("Profession with id: %d not found", id))));
    }
}
