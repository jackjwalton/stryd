package com.stryd.workoutscheduler.domain;

public class Trainer {

    private int trainerId;
    private String trainerName;


    public Trainer(int trainerId, String trainerName) {
        this.trainerId = trainerId;
        this.trainerName = trainerName;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

}
