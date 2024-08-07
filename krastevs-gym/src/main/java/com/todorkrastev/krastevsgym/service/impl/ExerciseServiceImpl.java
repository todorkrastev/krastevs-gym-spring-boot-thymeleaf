package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.exception.*;
import com.todorkrastev.krastevsgym.model.dto.*;
import com.todorkrastev.krastevsgym.model.entity.*;
import com.todorkrastev.krastevsgym.repository.ExerciseNoteRepository;
import com.todorkrastev.krastevsgym.repository.ExerciseRepository;
import com.todorkrastev.krastevsgym.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final ModelMapper modelMapper;
    private final ExerciseCategoryService exerciseCategoryService;
    private final UserService userService;
    private final EquipmentTypeService equipmentTypeService;
    private final ExerciseNoteService exerciseNoteService;
    private final ExerciseNoteRepository exerciseNoteRepository;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, ModelMapper modelMapper, ExerciseCategoryService exerciseCategoryService, UserService userService, EquipmentTypeService equipmentTypeService, ExerciseNoteService exerciseNoteService, ExerciseNoteRepository exerciseNoteRepository) {
        this.exerciseRepository = exerciseRepository;
        this.modelMapper = modelMapper;
        this.exerciseCategoryService = exerciseCategoryService;
        this.userService = userService;
        this.equipmentTypeService = equipmentTypeService;
        this.exerciseNoteService = exerciseNoteService;
        this.exerciseNoteRepository = exerciseNoteRepository;
    }

    @Override
    public Long createExercise(CreateExerciseDTO createExerciseDTO) {
        UserEntity currUser = userService.findUserById(createExerciseDTO.getCurrUserId());
        if (currUser == null) {
            throw new ResourceNotFoundException("User", "id", createExerciseDTO.getCurrUserId());
        }

        ExerciseCategoryEntity category = exerciseCategoryService.findByCategory(createExerciseDTO.getCategory());
        if (category == null) {
            throw new CategoryNotFoundException("Category", "id", createExerciseDTO.getCategory().toString());
        }

        EquipmentTypeEntity equipmentType = equipmentTypeService.findByCategory(createExerciseDTO.getEquipmentType());
        if (equipmentType == null) {
            throw new DepartmentNotFoundException("Equipment type", "id", createExerciseDTO.getEquipmentType().toString());
        }

        String gifUrl = createExerciseDTO.getGifUrl();
        if (gifUrl.isBlank()) {
            gifUrl = category.getMusclesWorked();
        }

        ExerciseEntity exercise = modelMapper.map(createExerciseDTO, ExerciseEntity.class);
        exercise.setId(null);
        exercise.setGifUrl(gifUrl).setMusclesWorkedUrl(category.getMusclesWorked()).setCategory(category).setUser(currUser);
        exercise.setEquipmentType(equipmentType);

        ExerciseEntity saved = exerciseRepository.save(exercise);

        return saved.getId();
    }

    @Override
    public void createExerciseNotes(CreateExerciseNotesDTO createExerciseNotesDTO, Long exerciseId, Long currentUserId) {
        if (createExerciseNotesDTO.getNotes().isEmpty() || createExerciseNotesDTO.getNotes().isBlank()) {
            return;
        }

        ExerciseEntity exercise = exerciseRepository.findById(exerciseId).orElseThrow(() -> new ResourceNotFoundException("Exercise", "id", exerciseId));

        Long admin = userService.findAdminId();
        if (admin == null) {
            throw new ResourceNotFoundException("Admin", "id", null);
        }

        if (!exercise.getUser().getId().equals(admin)) {
            if (!exercise.getUser().getId().equals(currentUserId)) {
                throw new UnauthorizedException("You are not authorized to add notes to this exercise.");
            }
        }

        UserEntity author = userService.findUserById(currentUserId);
        if (author == null) {
            throw new ResourceNotFoundException("User", "id", currentUserId);
        }

        ExerciseNoteEntity notes = new ExerciseNoteEntity();
        modelMapper.map(createExerciseNotesDTO, notes);
        notes
                .setExercise(exercise)
                .setAuthor(author);

        exercise.setNotes(List.of(notes));

        exerciseNoteRepository.save(notes);
    }

    @Override
    public ExerciseDetailsDTO editExercise(Long id, ExerciseDetailsDTO editExerciseDTO, Long authorId) {
        ExerciseNoteEntity note = exerciseNoteService.findByExerciseIdAndAuthorId(id, authorId);
        if (note != null) {
            if (editExerciseDTO.getNotes().isEmpty() || editExerciseDTO.getNotes().isBlank()) {
                note.setNotes("Type your notes here...");
                exerciseNoteRepository.save(note);
            } else {
                note.setNotes(editExerciseDTO.getNotes());
                exerciseNoteRepository.save(note);
            }
        }

        ExerciseEntity exercise = exerciseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Exercise", "id", id));

        if (!Objects.equals(exercise.getUser().getId(), authorId)) {
            throw new UnauthorizedException("You are not authorized to edit this exercise.");
        }

        UserEntity user = userService.findUserById(authorId);
        if (user == null) {
            throw new ResourceNotFoundException("User", "id", authorId);
        }

        ExerciseCategoryEntity category = exerciseCategoryService.findByCategory(editExerciseDTO.getCategory());
        if (category == null) {
            throw new CategoryNotFoundException("Category", "id", editExerciseDTO.getCategory().toString());
        }

        EquipmentTypeEntity equipmentType = equipmentTypeService.findByCategory(editExerciseDTO.getEquipmentType());
        if (equipmentType == null) {
            throw new EquipmentTypeNotFoundException("Equipment type", "category", editExerciseDTO.getEquipmentType().toString());
        }

        if (note == null) {
            if (!editExerciseDTO.getNotes().isEmpty() && !editExerciseDTO.getNotes().isBlank()) {
                ExerciseNoteEntity created = new ExerciseNoteEntity()
                        .setNotes(editExerciseDTO.getNotes())
                        .setExercise(exercise)
                        .setAuthor(user);

                exercise.setNotes(List.of(created));

                exerciseNoteRepository.save(created);
            }
        }

        String gifUrl = editExerciseDTO.getGifUrl();
        if (gifUrl == null || gifUrl.isEmpty() || gifUrl.isBlank()) {
            gifUrl = category.getMusclesWorked();
            editExerciseDTO.setGifUrl(gifUrl);
        }

        String muscleWorkedUrl = editExerciseDTO.getMusclesWorkedUrl();
        if (muscleWorkedUrl == null || muscleWorkedUrl.isEmpty() || muscleWorkedUrl.isBlank()) {
            muscleWorkedUrl = category.getMusclesWorked();
            editExerciseDTO.setMusclesWorkedUrl(muscleWorkedUrl);
        }

        ExerciseEntity exerciseEntity = modelMapper.map(editExerciseDTO, ExerciseEntity.class);
        exerciseEntity.setUser(user);
        exerciseEntity.setCategory(category);
        exerciseEntity.setEquipmentType(equipmentType);
        exerciseEntity.setCreatedAt(exercise.getCreatedAt());
        exerciseEntity.setNotes(exercise.getNotes());

        exerciseRepository.save(exerciseEntity);

        return modelMapper.map(exerciseEntity, ExerciseDetailsDTO.class);
    }

    @Override
    public List<ExerciseShortInfoDTO> getExercisesByTypeAndUserId(Long typeId, Long userId, Long categoryId) {
        equipmentTypeService.findById(typeId);

        Long adminId = userService.findAdminId();
        if (adminId == null) {
            throw new ResourceNotFoundException("Admin", "id", null);
        }

        List<ExerciseEntity> allExercisesByType = exerciseRepository.findAllByTypeIdAndAdminIdAndUserIdAndCategoryId(typeId, adminId, userId, categoryId);

        if ((long) allExercisesByType.size() == 0) {
            throw new ResourceNotFoundException("Exercises", "equipment type id", typeId);
        }

        return allExercisesByType.stream().map(exercise -> {
            ExerciseShortInfoDTO dto = modelMapper.map(exercise, ExerciseShortInfoDTO.class);
            dto.setExerciseName(truncateString(dto.getExerciseName()));
            return dto;
        }).toList();
    }

    @Override
    public void editExerciseNotes(CreateExerciseNotesDTO createExerciseNotesDTO, Long exerciseId, Long authorId) {
        ExerciseNoteEntity note = exerciseNoteService.findByExerciseIdAndAuthorId(exerciseId, authorId);
        if (note == null) {
            throw new NoteFoundException(exerciseId, authorId);
        }

        if (createExerciseNotesDTO.getNotes().isEmpty() || createExerciseNotesDTO.getNotes().isBlank()) {
            createExerciseNotesDTO.setNotes("Type your notes here...");
        }

        modelMapper.map(createExerciseNotesDTO, note);
        exerciseNoteRepository.save(note);
    }

    @Override
    public boolean isTheCreatorOfTheExercise(Long id, Long authorId) {
        return exerciseRepository.existsByIdAndUserId(id, authorId);
    }

    @Override
    public Long deleteExercise(Long id) {
        Optional<ExerciseEntity> exerciseEntity = exerciseRepository.findById(id);
        if (exerciseEntity.isEmpty()) {
            throw new ResourceNotFoundException("Exercise", "id", id);
        }

        exerciseRepository.deleteById(id);

        return exerciseEntity.get().getCategory().getId();
    }

    @Override
    @Transactional
    public ExerciseDetailsDTO getExerciseDetails(Long exerciseId, Long currentUserId) {
        return this.exerciseRepository
                .findById(exerciseId)
                .map(exercise -> {
                    ExerciseDetailsDTO exerciseDetailsDTO = modelMapper.map(exercise, ExerciseDetailsDTO.class);
                    exerciseDetailsDTO.setCreatorId(exercise.getUser().getId());
                    ExerciseNoteEntity note = exerciseNoteService.findByExerciseIdAndAuthorId(exerciseId, currentUserId);
                    if (note != null) {
                        exerciseDetailsDTO.setNotes(note.getNotes());
                    } else {
                        exerciseDetailsDTO.setNotes("");
                    }

                    return exerciseDetailsDTO;
                })
                .orElseThrow(() -> new ResourceNotFoundException("Exercise", "id", exerciseId));
    }

    @Override
    public List<ExerciseShortInfoDTO> getExercisesByCategoryIdAndUserId(Long categoryId, Long userId) {
        exerciseCategoryService.findById(categoryId);

        Long adminId = userService.findAdminId();
        if (adminId == null) {
            throw new ResourceNotFoundException("Admin", "id", null);
        }
        List<ExerciseEntity> allExercisesByCategoryId = exerciseRepository.findAllByCategoryIdAndAdminIdAndUserId(categoryId, adminId, userId);

        if ((long) allExercisesByCategoryId.size() == 0) {
            throw new ResourceNotFoundException("Exercises", "category id", categoryId);
        }

        return allExercisesByCategoryId.stream().map(exercise -> {
            ExerciseShortInfoDTO dto = modelMapper.map(exercise, ExerciseShortInfoDTO.class);
            dto.setExerciseName(truncateString(dto.getExerciseName()));
            return dto;
        }).toList();
    }

    private String truncateString(String input) {
        if (input.length() > 19) {
            return input.substring(0, 19) + "...";
        } else {
            return input;
        }
    }
}