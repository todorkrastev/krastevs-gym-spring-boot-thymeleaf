package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.model.dto.ActivityDTO;
import com.todorkrastev.krastevsgym.service.ActivityService;
import com.todorkrastev.krastevsgym.web.aop.LogActivityExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {
    private final Logger LOGGER = LoggerFactory.getLogger(ExerciseServiceImpl.class);

    private final RestClient activityRestClient;

    public ActivityServiceImpl(@Qualifier("activityRestClient") RestClient activityRestClient) {
        this.activityRestClient = activityRestClient;
    }

    @LogActivityExecution
    @Override
    public List<ActivityDTO> findAll() {
        try {
            List<ActivityDTO> response = activityRestClient
                    .get()
                    .uri("http://localhost:8081/api/v1/activities/all")
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });

            if (response == null) {
                LOGGER.error("Received null response from activityRestClient");
                return List.of();
            }

            return response;
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching all activities: ", e);
            return List.of();
        }
    }


    @Override
    public ActivityDTO getActivityById(Long activityId) {
        return null;
    }

    @Override
    public ActivityDTO updateActivityById(Long activityId, ActivityDTO activityDTO) {
        return null;
    }

    @Override
    public Long createActivity(ActivityDTO activityDTO) {
        return null;
    }

    @Override
    public void deleteActivityById(Long activityId) {
    }
}
