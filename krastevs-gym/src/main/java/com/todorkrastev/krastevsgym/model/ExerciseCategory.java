package com.todorkrastev.krastevsgym.model;

import jakarta.persistence.*;

@Entity
@Table(name = "exercise_categories")
public class ExerciseCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Enumerated(EnumType.STRING)
    public ExerciseCategoryEnum exerciseCategory;

    public ExerciseCategory() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ExerciseCategoryEnum getExerciseCategory() {
        return exerciseCategory;
    }

    public void setExerciseCategory(ExerciseCategoryEnum exerciseCategory) {
        this.exerciseCategory = exerciseCategory;
    }
}
