package com.todorkrastev.krastevsgymexercises.web;

import com.todorkrastev.krastevsgymexercises.model.dto.CreateExerciseDTO;
import com.todorkrastev.krastevsgymexercises.model.dto.ExerciseDetailsDTO;
import com.todorkrastev.krastevsgymexercises.service.ExerciseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/exercises")
public class ExerciseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExerciseController.class);
    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseDetailsDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(exerciseService.getOfferById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ExerciseDetailsDTO> deleteById(@PathVariable("id") Long id) {
        exerciseService.deleteExercise(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ExerciseDetailsDTO>> getAllExercises() {
        return ResponseEntity.ok(exerciseService.getAllOffers());
    }

    @PostMapping
    public ResponseEntity<ExerciseDetailsDTO> createExercise(@RequestBody CreateExerciseDTO createExerciseDTO) {
        LOGGER.info("Creating an offer {}", createExerciseDTO);

        ExerciseDetailsDTO exerciseDetailsDTO = exerciseService.createExercise(createExerciseDTO);
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(exerciseDetailsDTO.id())
                        .toUri()
        ).body(exerciseDetailsDTO);
    }
}
