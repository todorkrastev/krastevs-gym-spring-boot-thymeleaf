package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.exception.ResourceNotFoundException;
import com.todorkrastev.krastevsgym.model.dto.ActivityDTO;
import com.todorkrastev.krastevsgym.model.entity.ActivityEntity;
import com.todorkrastev.krastevsgym.repository.ActivityRepository;
import com.todorkrastev.krastevsgym.service.ActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.modelmapper.ModelMapper;
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
    private final ActivityRepository activityRepository;
    private final ModelMapper modelMapper;

    public ActivityServiceImpl(@Qualifier("activityRestClient")RestClient activityRestClient, ActivityRepository activityRepository, ModelMapper modelMapper) {
        this.activityRestClient = activityRestClient;
        this.activityRepository = activityRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ActivityDTO> findAll() {
        LOGGER.info("----------------GET ALL ACTIVITIES----------------");

        return activityRestClient
                .get()
                .uri("http://localhost:8081/activities")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    @Override
    public ActivityDTO getActivityById(Long activityId) {
        ActivityEntity activity = this.activityRepository.findById(activityId).orElseThrow(() -> new ResourceNotFoundException("Activity", "id", activityId));

        return this.modelMapper.map(activity, ActivityDTO.class);

        /*
        return this.activityRepository
                .findById(activityId)
                .map(activity ->
                        this.modelMapper
                                .map(activity, ActivityDTO.class));

         */
    }

    @Override
    public ActivityDTO updateActivityById(Long activityId, ActivityDTO activityDTO) {
        //TODO: Make a validation if the admin is doing the change

        ActivityEntity activity = this.activityRepository.findById(activityId).orElseThrow(() -> new ResourceNotFoundException("Activity", "id", activityId));

        activity.setTitle(activityDTO.getTitle());
        activity.setDescription(activityDTO.getDescription());
        activity.setImageURL(activityDTO.getImageURL());

        ActivityEntity updatedActivity = this.activityRepository.save(activity);

        return this.modelMapper.map(updatedActivity, ActivityDTO.class);
    }

    @Override
    public Long createActivity(ActivityDTO activityDTO) {
        ActivityEntity activityToSave = this.modelMapper.map(activityDTO, ActivityEntity.class);

        this.activityRepository.save(activityToSave);

        return activityToSave.getId();
    }

    @Override
    public void deleteActivityById(Long activityId) {
        ActivityEntity activity = this.activityRepository.findById(activityId).orElseThrow(() -> new ResourceNotFoundException("Activity", "id", activityId));

        this.activityRepository.delete(activity);
    }
}
