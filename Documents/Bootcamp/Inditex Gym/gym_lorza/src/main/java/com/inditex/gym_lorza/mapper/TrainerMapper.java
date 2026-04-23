package com.inditex.gym_lorza.mapper;

import com.inditex.gym_lorza.dto.TrainerRequestDTO;
import com.inditex.gym_lorza.dto.TrainerResponseDTO;
import com.inditex.gym_lorza.model.Trainer;

public class TrainerMapper {

    public static Trainer toEntity(TrainerRequestDTO dto) {
        Trainer trainer = new Trainer();
        trainer.setName(dto.getName());
        trainer.setDni(dto.getDni());
        trainer.setSpeciality(dto.getSpeciality());
        trainer.setExperience(dto.getExperience());
        trainer.setHiringYear(dto.getHiringYear());
        trainer.setIsHired(dto.getIsHired());
        trainer.setImage(dto.getImage());
        return trainer;
    }

    public static TrainerResponseDTO toDTO(Trainer trainer) {
        TrainerResponseDTO dto = new TrainerResponseDTO();
        dto.setId(trainer.getId());
        dto.setName(trainer.getName());
        dto.setDni(trainer.getDni());
        dto.setSpeciality(trainer.getSpeciality());
        dto.setExperience(trainer.getExperience());
        dto.setHiringYear(trainer.getHiringYear());
        dto.setIsHired(trainer.getIsHired());
        dto.setImage(trainer.getImage());
        return dto;
    }
}
