package com.stryd.workoutscheduler.service;

import com.stryd.workoutscheduler.domain.Trainer;
import com.stryd.workoutscheduler.domain.TrainerDayCalendar;
import com.stryd.workoutscheduler.domain.Workout;
import com.stryd.workoutscheduler.repository.TrainerCalendarRepository;
import com.stryd.workoutscheduler.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrainerCalendarService {


    private TrainerCalendarRepository trainerCalendarRepository;
    private TrainerRepository trainerRepository;

    @Autowired
    public TrainerCalendarService(TrainerCalendarRepository trainerCalendarRepository
            , TrainerRepository trainerRepository) {
        this.trainerCalendarRepository = trainerCalendarRepository;
        this.trainerRepository = trainerRepository;
    }


    public List<TrainerDayCalendar> getAllCalendars() {
        return trainerCalendarRepository.getAllCalendars();
    }

    public TrainerDayCalendar getCalendarById(int trainerId) {
        Optional<Trainer> trainer = trainerRepository.findTrainerById(trainerId);
        if (trainer.isPresent()) {
            Optional<TrainerDayCalendar> calendar = trainerCalendarRepository.getTrainerCalendar(trainer.get());
            return calendar.orElse(null);
        } else {
            return null;
        }

    }


    public TrainerDayCalendar addWorkout(int trainerId, Workout workout) {
        Optional<Trainer> trainerOpt = trainerRepository.findTrainerById(trainerId);
        if (trainerOpt.isEmpty()) {
            return null;
        } else {
            Trainer trainer = trainerOpt.get();
            Optional<TrainerDayCalendar> existingCalendar = trainerCalendarRepository.getTrainerCalendar(trainer);

            TrainerDayCalendar trainerDayCalendar;
            if (existingCalendar.isEmpty()) {
                TrainerDayCalendar newCalendar = new TrainerDayCalendar(trainer);
                trainerDayCalendar = trainerCalendarRepository.addTrainerCalendar(newCalendar);
            } else {
                trainerDayCalendar = existingCalendar.get();
            }

            if (containsConflictingWorkout(trainerDayCalendar, workout)) {
                return null;
            } else {
                return trainerCalendarRepository.addWorkout(trainerDayCalendar, workout);
            }

        }
    }


    private boolean containsConflictingWorkout(TrainerDayCalendar trainerDayCalendar, Workout workout) {
        int startingHour = workout.getStartTime();

        return trainerDayCalendar.getSchedule().stream()
                .anyMatch(calWorkout -> calWorkout.getStartTime() == startingHour);
    }


    public List<Integer> getAvailableTimeSlotsById(int trainerId) {
        Optional<Trainer> trainerOpt = trainerRepository.findTrainerById(trainerId);
        if (trainerOpt.isEmpty()) {
            return null;
        } else {
            Trainer trainer = trainerOpt.get();
            Optional<TrainerDayCalendar> existingCalendar = trainerCalendarRepository.getTrainerCalendar(trainer);

            if (existingCalendar.isEmpty()) {
                return ALL_TIME_SLOTS;
            } else {
                List<Integer> takenTimeSlots = existingCalendar.get().getSchedule()
                        .stream().map(Workout::getStartTime).collect(Collectors.toList());


                ArrayList<Integer> availableTimeSlots = new ArrayList<>(ALL_TIME_SLOTS);
                availableTimeSlots.removeAll(takenTimeSlots);

                return availableTimeSlots;

            }

        }
    }


    private static final List<Integer> ALL_TIME_SLOTS = Arrays.asList(7, 8, 9, 10, 11, 12, 1, 2, 3, 4);
    //Extended working hours (7-5)
    //Not timezone supported/standardized. I only have an hour :p
    //Workouts are always 1 hour long. This logic would change once that wasn't the case.

}
