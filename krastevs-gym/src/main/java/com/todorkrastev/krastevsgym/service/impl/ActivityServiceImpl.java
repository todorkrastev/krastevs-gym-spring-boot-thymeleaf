package com.todorkrastev.krastevsgym.service.impl;

import com.todorkrastev.krastevsgym.exception.ResourceNotFoundException;
import com.todorkrastev.krastevsgym.model.dto.ActivityDTO;
import com.todorkrastev.krastevsgym.model.entity.ActivityEntity;
import com.todorkrastev.krastevsgym.repository.ActivityRepository;
import com.todorkrastev.krastevsgym.service.ActivityService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ActivityServiceImpl implements ActivityService {
    private final ActivityRepository activityRepository;
    private final ModelMapper modelMapper;

    public ActivityServiceImpl(ActivityRepository activityRepository, ModelMapper modelMapper) {
        this.activityRepository = activityRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Set<ActivityDTO> findAll() {
        return this.activityRepository
                .findAll()
                .stream()
                .map(activity -> this.modelMapper.map(activity, ActivityDTO.class))
                .collect(Collectors.toSet());
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

        activity.setTitle(activityDTO.title());
        activity.setDescription(activityDTO.description());
        activity.setImage(activityDTO.image());

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
