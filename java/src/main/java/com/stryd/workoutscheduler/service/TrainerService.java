package com.stryd.workoutscheduler.service;

import com.stryd.workoutscheduler.domain.Trainer;
import com.stryd.workoutscheduler.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerService {

    private TrainerRepository trainerRepository;

    @Autowired
    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }


    public Trainer findTrainerById(int trainerId) {
        Optional<Trainer> trainer = trainerRepository.findTrainerById(trainerId);
        return trainer.orElse(null);
    }

    public List<Trainer> getAllTrainers() {
        return trainerRepository.getAllTrainers();
    }

    public Trainer addOrUpdateTrainer(Trainer trainer) {
        if (findTrainerById(trainer.getTrainerId()) != null) {
            return trainerRepository.updateTrainer(trainer);
        } else {
            return trainerRepository.addTrainer(trainer);
        }
    }

    public boolean deleteTrainer(int trainerId) {

        if (findTrainerById(trainerId) != null) {
            trainerRepository.deleteTrainer(trainerId);
            return true;
        } else {
            return false;
        }
    }

}
