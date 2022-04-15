package com.stryd.workoutscheduler.controller;

import com.stryd.workoutscheduler.domain.TrainerDayCalendar;
import com.stryd.workoutscheduler.domain.Workout;
import com.stryd.workoutscheduler.service.TrainerCalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TrainerDayCalendarController {

    private TrainerCalendarService trainerCalendarService;

    @Autowired
    public TrainerDayCalendarController(TrainerCalendarService trainerCalendarService){
        this.trainerCalendarService = trainerCalendarService;
    }


    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    // Add your new REST endpoint(s) here


    @GetMapping("/calendars/{id}")
    public TrainerDayCalendar getWorkoutsById(@RequestParam(value = "id") int trainerId){
        return trainerCalendarService.getCalendarById(trainerId);
    }

    @GetMapping("/calendars")
    public List<TrainerDayCalendar> getAllCalendars(){
        return trainerCalendarService.getAllCalendars();
    }


    @PostMapping("/calendars/{id}")
    public TrainerDayCalendar addWorkout(@RequestParam(value = "id") int trainerId, Workout workout){
        return trainerCalendarService.addWorkout(trainerId, workout);
    }


    //Example: Available times
    //Ideally would be handled by the front end (schedule/calendar object populated with data from getWorkoutsById()
    //This is a backend example for the exact usecase.
    @GetMapping("/calendars/{id}/available")
    public List<Integer> getAvailableWorkoutSlotsById(@RequestParam(value = "id") int trainerId){
        return trainerCalendarService.getAvailableTimeSlotsById(trainerId);
    }
}
