package com.stryd.workoutscheduler.domain;

import java.util.ArrayList;
import java.util.List;

public class TrainerDayCalendar {

    private Trainer trainer;
    private List<Workout> schedule;

    public TrainerDayCalendar(Trainer trainer){
        this.trainer = trainer;
        this.schedule = new ArrayList<>();
    }

    public TrainerDayCalendar(Trainer trainer, List<Workout> schedule){
        this.trainer = trainer;
        this.schedule = schedule;
    }


    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public List<Workout> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Workout> schedule) {
        this.schedule = schedule;
    }

    public void addWorkout(Workout workout){
        this.schedule.add(workout);
    }

}
