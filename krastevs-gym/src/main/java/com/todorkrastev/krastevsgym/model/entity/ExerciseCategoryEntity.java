package com.todorkrastev.krastevsgym.model.entity;

import com.todorkrastev.krastevsgym.model.enums.ExerciseCategoryEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "exercise_categories")
public class ExerciseCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Enumerated(EnumType.STRING)
    public ExerciseCategoryEnum exerciseCategory;

    public ExerciseCategoryEntity() {
    }

    public long getId() {
        return id;
    }

    public ExerciseCategoryEntity setId(long id) {
        this.id = id;
        return this;
    }

    public ExerciseCategoryEnum getExerciseCategory() {
        return exerciseCategory;
    }

    public ExerciseCategoryEntity setExerciseCategory(ExerciseCategoryEnum exerciseCategory) {
        this.exerciseCategory = exerciseCategory;
        return this;
    }
}
