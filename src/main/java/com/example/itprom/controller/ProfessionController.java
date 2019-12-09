package com.example.itprom.controller;

import com.example.itprom.dto.ProfessionDto;
import com.example.itprom.exception.ProfessionNotFoundException;
import com.example.itprom.service.ProfessionService;
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
@RequestMapping("/api/professions")
public class ProfessionController {

    private final ProfessionService professionService;

    @Autowired
    public ProfessionController(ProfessionService professionService) {
        this.professionService = professionService;
    }

    @GetMapping
    private ResponseEntity<?> getAllProfession() {
        return ResponseEntity.ok(professionService.getAllProfession());
    }

    @GetMapping("/{id}")
    private ResponseEntity<ProfessionDto> getProfessionById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(professionService.getProfessionById(id));
        } catch (ProfessionNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<?> update(@Valid @RequestBody ProfessionDto professionDto) {
        return ResponseEntity.ok(professionService.update(professionDto));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            professionService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ProfessionNotFoundException exception) {
            return ResponseEntity.notFound().build();
        }
    }
}
