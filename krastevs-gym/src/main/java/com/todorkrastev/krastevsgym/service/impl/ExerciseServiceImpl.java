package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.model.dto.CreateExerciseDTO;
import com.todorkrastev.krastevsgym.model.dto.ExerciseDetailsDTO;
import com.todorkrastev.krastevsgym.model.dto.ExerciseShortInfoDTO;
import com.todorkrastev.krastevsgym.model.entity.ExerciseEntity;
import com.todorkrastev.krastevsgym.model.entity.PictureEntity;
import com.todorkrastev.krastevsgym.model.enums.ExerciseCategoryEnum;
import com.todorkrastev.krastevsgym.repository.ExerciseRepository;
import com.todorkrastev.krastevsgym.service.ExerciseService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ModelMapper modelMapper;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, ModelMapper modelMapper) {
        this.exerciseRepository = exerciseRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public List<ExerciseShortInfoDTO> getAll() {
        return exerciseRepository.findAll()
                .stream()
                .map(this::mapToInfo)
                .toList();
    }

    @Override
    public long createExercise(CreateExerciseDTO createExerciseDTO) {
        ExerciseEntity exercise = new ExerciseEntity()
                .setName(createExerciseDTO.name())
                .setDescription(createExerciseDTO.description())
                .setEquipmentTypeEnum(createExerciseDTO.equipmentTypeEnum())
                .setExerciseCategory(createExerciseDTO.exerciseCategoryEnum())
                .setInstructions(createExerciseDTO.instructions())
                .setGifUrl(createExerciseDTO.videoUrl());


        //  ExerciseEntity exercise = modelMapper.map(createExerciseDTO, ExerciseEntity.class);
        return exerciseRepository.save(exercise).getId();
    }

    @Override
    public ExerciseDetailsDTO getExerciseDetails(Long id) {
        return this.exerciseRepository
                .findById(id)
                .map(ExerciseServiceImpl::toExerciseDetails)
                .orElseThrow();
    }

    @Override
    public List<ExerciseShortInfoDTO> getExercisesByGivenCategory(ExerciseCategoryEnum exerciseCategory) {
        List<ExerciseEntity> exercisesByCategory = exerciseRepository.findAllByExerciseCategory(exerciseCategory);

        return exercisesByCategory.stream().map(exerciseEntity -> modelMapper.map(exerciseEntity, ExerciseShortInfoDTO.class)).toList();
    }

    private static ExerciseDetailsDTO toExerciseDetails(ExerciseEntity exerciseEntity) {
        return new ExerciseDetailsDTO(exerciseEntity.getId(),
                exerciseEntity.getName(),
                exerciseEntity.getDescription(),
                exerciseEntity.getGifUrl(),
                exerciseEntity.getMusclesWorkedUrl(),
                exerciseEntity.getInstructions(),
                exerciseEntity.getNotes());
    }

    private ExerciseShortInfoDTO mapToInfo(ExerciseEntity exercise) {
        ExerciseShortInfoDTO dto = modelMapper.map(exercise, ExerciseShortInfoDTO.class);

        Optional<PictureEntity> first = exercise.getPictures().stream().findFirst();
        first.ifPresent(pictureEntity -> dto.setGifUrl(pictureEntity.getUrl()));

        return dto;
    }
}
