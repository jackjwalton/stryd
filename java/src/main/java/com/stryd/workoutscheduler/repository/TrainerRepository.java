package com.stryd.workoutscheduler.repository;

import com.stryd.workoutscheduler.domain.Trainer;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Repository
public class TrainerRepository {

    static Trainer T1 = new Trainer(1, "T1");
    static Trainer T2 = new Trainer(2, "T2");
    static Trainer T3 = new Trainer(3, "T3");

    public static List<Trainer> trainerTestDB = Arrays.asList(T1, T2, T3);


    public Optional<Trainer> findTrainerById(int trainerId) {
        return trainerTestDB.stream()
                .filter(trainer -> trainer.getTrainerId() == trainerId)
                .findFirst();
    }

    public List<Trainer> getAllTrainers() {
        return trainerTestDB;
    }

    public Trainer addTrainer(Trainer trainer) {
        trainerTestDB.add(trainer);
        return trainer;
    }

    public Trainer updateTrainer(Trainer trainer) {
        trainerTestDB.stream()
                .map(t -> t.getTrainerId() == trainer.getTrainerId() ? trainer : t)
                .collect(toList());


        return findTrainerById(trainer.getTrainerId()).orElse(null);
    }


    public void deleteTrainer(int trainerId) {
        trainerTestDB.stream()
                .filter(trainer -> trainer.getTrainerId() != trainerId)
                .collect(toList());
    }
}
