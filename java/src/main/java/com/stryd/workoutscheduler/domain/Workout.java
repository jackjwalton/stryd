package com.stryd.workoutscheduler.domain;

public class Workout {

    private String scrubName;
    private int startingHour;


    public Workout(String scrubName, int startingHour) {
        this.scrubName = scrubName;
        this.startingHour = startingHour;
    }


    public String getScrubName() {
        return scrubName;
    }

    public void setScrubName(String scrubName) {
        this.scrubName = scrubName;
    }

    public int getStartTime() {
        return startingHour;
    }

    public void setStartTime(int startingHour) {
        this.startingHour = startingHour;
    }



}
