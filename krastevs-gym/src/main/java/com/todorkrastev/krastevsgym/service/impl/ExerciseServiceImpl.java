package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.model.dto.CreateExerciseDTO;
import com.todorkrastev.krastevsgym.model.dto.ExerciseShortInfoDTO;
import com.todorkrastev.krastevsgym.model.entity.ExerciseEntity;
import com.todorkrastev.krastevsgym.model.entity.PictureEntity;
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
    public void createExercise(CreateExerciseDTO createExerciseDTO) {
        exerciseRepository.save(map(createExerciseDTO));
    }

    private ExerciseShortInfoDTO mapToInfo(ExerciseEntity exercise) {
        ExerciseShortInfoDTO dto = modelMapper.map(exercise, ExerciseShortInfoDTO.class);

        Optional<PictureEntity> first = exercise.getPictures().stream().findFirst();
        first.ifPresent(pictureEntity -> dto.setImageUrl(pictureEntity.getUrl()));

        return dto;
    }

    private static ExerciseEntity map(CreateExerciseDTO createExerciseDTO) {
        return new ExerciseEntity()
                .setExerciseName(createExerciseDTO.exerciseName())
                .setEquipmentTypeEnum(createExerciseDTO.equipmentTypeEnum())
                .setInstructions(createExerciseDTO.instructions());
    }
}
