package com.example.itprom.utils;

import com.example.itprom.domain.Profession;
import com.example.itprom.dto.ProfessionDto;

public final class ProfessionMapper {

    public static ProfessionDto map(Profession profession) {
        ProfessionDto result = new ProfessionDto();
        result.setId(profession.getId());
        result.setName(profession.getName());
        result.setNote(profession.getNote());

        return result;
    }

    public static Profession map(ProfessionDto professionDto) {
        Profession result = new Profession();
        result.setId(professionDto.getId());
        result.setName(professionDto.getName());
        result.setNote(professionDto.getNote());

        return result;
    }
}
