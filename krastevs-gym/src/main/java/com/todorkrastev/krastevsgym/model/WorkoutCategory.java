package com.todorkrastev.krastevsgym.model;

import jakarta.persistence.*;

@Entity
@Table(name = "workout_categories")
public class WorkoutCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private WorkoutCategoryEnum workoutCategoryEnum;

    public WorkoutCategory() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public WorkoutCategoryEnum getWorkoutCategoryEnum() {
        return workoutCategoryEnum;
    }

    public void setWorkoutCategoryEnum(WorkoutCategoryEnum workoutCategoryEnum) {
        this.workoutCategoryEnum = workoutCategoryEnum;
    }
}
