package com.inditex.gym_lorza.service;

import com.inditex.gym_lorza.dto.ActivityRequestDTO;
import com.inditex.gym_lorza.dto.ActivityResponseDTO;
import com.inditex.gym_lorza.exception.ObjectNotFoundException;
import com.inditex.gym_lorza.mapper.ActivityMapper;
import com.inditex.gym_lorza.model.Activity;
import com.inditex.gym_lorza.model.Trainer;
import com.inditex.gym_lorza.repository.ActivityRepository;
import com.inditex.gym_lorza.repository.TrainerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final TrainerRepository trainerRepository;

    public ActivityService(ActivityRepository activityRepository, TrainerRepository trainerRepository) {
        this.activityRepository = activityRepository;
        this.trainerRepository = trainerRepository;
    }

    public List<ActivityResponseDTO> getAll() {
        return activityRepository.findAll()
                .stream()
                .map(ActivityMapper::toDTO)
                .toList();
    }

    public ActivityResponseDTO findActivity(Long id) {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("actividad", id));
        return ActivityMapper.toDTO(activity);
    }

    public ActivityResponseDTO addActivity(ActivityRequestDTO dto) {
        Activity activity = ActivityMapper.toEntity(dto);
        activity.setTrainer(resolveTrainer(dto.getTrainerId()));
        return ActivityMapper.toDTO(activityRepository.save(activity));
    }

    public void deleteActivity(Long id) {
        if (!activityRepository.existsById(id)) {
            throw new ObjectNotFoundException("actividad", id);
        }
        activityRepository.deleteById(id);
    }

    public ActivityResponseDTO updateActivity(Long id, ActivityRequestDTO dto) {
        Activity existingActivity = activityRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("actividad", id));

        existingActivity.setTitle(dto.getTitle());
        existingActivity.setPrice(dto.getPrice());
        existingActivity.setWeekDay(dto.getWeekDay());
        existingActivity.setStartHour(dto.getStartHour());
        existingActivity.setEndHour(dto.getEndHour());
        existingActivity.setImage(dto.getImage());
        existingActivity.setTrainer(resolveTrainer(dto.getTrainerId()));

        return ActivityMapper.toDTO(activityRepository.save(existingActivity));
    }

    private Trainer resolveTrainer(Long trainerId) {
        if (trainerId == null) {
            return null;
        }

        return trainerRepository.findById(trainerId)
                .orElseThrow(() -> new ObjectNotFoundException("entrenadora", trainerId));
    }
}
