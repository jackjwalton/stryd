package com.stryd.workoutscheduler.controller;

import com.stryd.workoutscheduler.domain.Trainer;
import com.stryd.workoutscheduler.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TrainerController {


    private TrainerService trainerService;


    @Autowired
    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }


    @GetMapping("/trainers")
    public List<Trainer> getAllTrainers() {
        return trainerService.getAllTrainers();
    }

    @GetMapping("/trainers/{id}")
    public Trainer findTrainerById(@RequestParam(value = "id") int id) {
        return trainerService.findTrainerById(id);
    }


    @PostMapping("/trainers")
    public Trainer addOrUpdateTrainer(Trainer trainer) {
        return trainerService.addOrUpdateTrainer(trainer);
    }

    @DeleteMapping("/trainers/{id}")
    public ResponseEntity<Integer> deletePost(@PathVariable int id) {

        boolean isRemoved = trainerService.deleteTrainer(id);

        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}
