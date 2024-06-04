package com.todorkrastev.krastevsgym.service;

import com.todorkrastev.krastevsgym.model.Exercise;
import com.todorkrastev.krastevsgym.model.Picture;
import com.todorkrastev.krastevsgym.model.dto.ExerciseShortInfoDTO;
import com.todorkrastev.krastevsgym.repository.ExerciseRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {

    private ExerciseRepository exerciseRepository;
    private ModelMapper modelMapper;

    public ExerciseService(ExerciseRepository exerciseRepository, ModelMapper modelMapper) {
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

    private ExerciseShortInfoDTO mapToInfo(Exercise exercise) {
        ExerciseShortInfoDTO dto = modelMapper.map(exercise, ExerciseShortInfoDTO.class);

        Optional<Picture> first = exercise.getPictures().stream().findFirst();
        first.ifPresent(picture -> dto.setImageUrl(picture.getUrl()));

        return dto;
    }
}
