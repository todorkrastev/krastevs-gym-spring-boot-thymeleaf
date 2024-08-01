package com.todorkrastev.krastevsgym.service;

import com.todorkrastev.krastevsgym.model.dto.ActivityDTO;
import com.todorkrastev.krastevsgym.model.dto.CreateActivityDTO;

import java.util.List;

public interface ActivityService {
    List<ActivityDTO> findAll();

    ActivityDTO getActivityById(Long activityId);

    void createActivity(CreateActivityDTO createActivityDTO);

    boolean doesTitleExist(String title);

    void updateActivity(Long id, ActivityDTO activityDTO);

    void deleteActivity(Long id);
}
