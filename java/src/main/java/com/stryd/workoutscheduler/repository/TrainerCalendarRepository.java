package com.stryd.workoutscheduler.repository;

import com.stryd.workoutscheduler.domain.Trainer;
import com.stryd.workoutscheduler.domain.TrainerDayCalendar;
import com.stryd.workoutscheduler.domain.Workout;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.stryd.workoutscheduler.repository.TrainerRepository.*;

@Repository
public class TrainerCalendarRepository {


    private static Workout mikeWorkout = new Workout("Mike", 9);
    private static Workout tyWorkout = new Workout("Ty", 12);
    private static Workout aliceWorkout = new Workout("Alice", 14); //2PM

    private static TrainerDayCalendar T1Calendar = new TrainerDayCalendar(T1, Arrays.asList(mikeWorkout, tyWorkout, aliceWorkout));
    private static TrainerDayCalendar T2Calendar = new TrainerDayCalendar(T2);
    private static TrainerDayCalendar T3Calendar = new TrainerDayCalendar(T3);

    private static List<TrainerDayCalendar> testTrainerDayCalendarList = Arrays.asList(T1Calendar, T2Calendar, T3Calendar);

    public Optional<TrainerDayCalendar> getTrainerCalendar(Trainer trainer){
        return testTrainerDayCalendarList.stream()
                .filter(calendar -> calendar.getTrainer().equals(trainer))
                .findFirst();
    }

    public List<TrainerDayCalendar> getAllCalendars() {
        return testTrainerDayCalendarList;
    }


    public TrainerDayCalendar addTrainerCalendar(TrainerDayCalendar trainerDayCalendar){
        testTrainerDayCalendarList.add(trainerDayCalendar);
        return trainerDayCalendar;
    }

    //would be handled better in the service layer
    public TrainerDayCalendar addWorkout(TrainerDayCalendar trainerDayCalendar, Workout workout){
        testTrainerDayCalendarList.stream()
                .filter(trainerCal -> trainerCal.equals(trainerDayCalendar))
                .forEach(trainerCal -> trainerCal.addWorkout(workout));

        return trainerDayCalendar;
    }

}
