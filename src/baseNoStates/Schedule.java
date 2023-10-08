package baseNoStates;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Schedule {

    private LocalDate fromDate;
    private LocalDate toDate;
    private List<DayOfWeek> workDays;
    private LocalTime fromHour;
    private LocalTime toHour;

    public Schedule(LocalDate initalDate, LocalDate finalDate, LocalTime initalHour, LocalTime finalHour, List<DayOfWeek> weekDays){
        fromDate = initalDate;
        toDate = finalDate;
        fromHour = initalHour;
        toHour = finalHour;
        workDays = weekDays;
    }

    public LocalDate getFromDate(){ return fromDate;}
    public LocalDate getToDate() { return toDate;}
    public LocalTime getFromHour() { return fromHour;}
    public LocalTime getToHour() { return toHour;}
    public List<DayOfWeek> getWorkDays() { return workDays;}
    public boolean isOnTime(LocalTime hour) {
        //If the hour is after inital hour and before end time, the function will return true, otherwise false.
        if( (fromHour.isAfter(hour)) && (toHour.isBefore(hour)) ){
            return true;
        }
        else{
            return false;
        }

    }
    public boolean isOnDate (LocalDate date){
        //If the date is after initial date and before end date it will return true, otherwise false.
        if( (fromDate.isBefore(date)) && (toDate.isAfter(date)) ){
            return true;
        }
        else{return false;}
    }


}
