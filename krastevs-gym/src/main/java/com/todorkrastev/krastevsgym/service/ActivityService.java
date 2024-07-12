package com.todorkrastev.krastevsgym.service;

import com.todorkrastev.krastevsgym.model.dto.ActivityDTO;

import java.util.Set;

public interface ActivityService {
    Set<ActivityDTO> findAll();

    ActivityDTO getActivityById(Long activityId);

    ActivityDTO updateActivityById(Long activityId, ActivityDTO activityDTO);

    Long createActivity(ActivityDTO newActivity);

    void deleteActivityById(Long activityId);
}
