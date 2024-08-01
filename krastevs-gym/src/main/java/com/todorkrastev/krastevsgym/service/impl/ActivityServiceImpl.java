package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.model.dto.ActivityDTO;
import com.todorkrastev.krastevsgym.model.dto.CreateActivityDTO;
import com.todorkrastev.krastevsgym.service.ActivityService;
import com.todorkrastev.krastevsgym.web.aop.LogActivityExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

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
        return activityRestClient
                .get()
                .uri("http://localhost:8081/api/v1/activities/" + activityId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(ActivityDTO.class);
    }

    @Override
    public void createActivity(CreateActivityDTO createActivityDTO) {
        try {
            activityRestClient
                    .post()
                    .uri("http://localhost:8081/api/v1/activities/create")
                    .body(createActivityDTO)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .toBodilessEntity();
        } catch (RestClientResponseException ex) {
            if (ex.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new RestClientResponseException("Validation failed", ex.getStatusCode().value(), ex.getStatusText(), ex.getResponseHeaders(), ex.getResponseBodyAsByteArray(), null);
            } else {
                throw ex;
            }
        }
    }

    @Override
    public boolean doesTitleExist(String title) {
        return Boolean.TRUE.equals(activityRestClient
                .get()
                .uri("http://localhost:8081/api/v1/activities/exists/" + title)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(Boolean.class));
    }

    @Override
    public void updateActivity(Long id, ActivityDTO activityDTO) {
        try {
            activityRestClient
                    .put()
                    .uri("http://localhost:8081/api/v1/activities/" + id)
                    .body(activityDTO)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .toBodilessEntity();
        } catch (RestClientResponseException ex) {
            if (ex.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new RestClientResponseException("Validation failed", ex.getStatusCode().value(), ex.getStatusText(), ex.getResponseHeaders(), ex.getResponseBodyAsByteArray(), null);
            } else {
                throw ex;
            }
        }
    }

    @Override
    public void deleteActivity(Long id) {
        activityRestClient
                .delete()
                .uri("http://localhost:8081/api/v1/activities/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toBodilessEntity();
    }
}
