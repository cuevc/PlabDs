package base.no.states;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


/**
 * Each group have a Schedule. The Schedule tells us the time and date
 * when the user can make an action.
 */
public class Schedule {
  static Logger logger = LoggerFactory.getLogger("door.state.DoorState.Unlocked");

  // From which date (the date when the user started working on the company
  private LocalDate fromDate;
  // To which date (the date when the user started working on the company
  private LocalDate toDate;
  // To check if in this day of the week the user is working (can access to the building).
  private List<DayOfWeek> workDays;
  // From which hour (the hour when the user start the shift).
  private LocalTime fromHour;
  // To which hour (the hour when the user start the shift).
  private LocalTime toHour;

  public Schedule(LocalDate initalDate, LocalDate finalDate, LocalTime initalHour,
                  LocalTime finalHour, List<DayOfWeek> weekDays) {
    fromDate = initalDate;
    toDate = finalDate;
    fromHour = initalHour;
    toHour = finalHour;
    workDays = weekDays;
  }


  // =====================================================
  // ||           Other methods of this class           ||
  // =====================================================

  //If the hour is after initial hour and before end time, the function will
  // return true, otherwise false.
  public boolean isOnTime(LocalTime hour) {
    if ((fromHour.isBefore(hour)) && (toHour.isAfter(hour))) {
      logger.debug("This user is on time");
      return true;
    } else {
      logger.debug("This user is not on time");
      return false;
    }
  }

  //If the date is after initial date and before end date it will return true, otherwise false.
  public boolean isOnDate(LocalDate date) {
    if ((fromDate.isBefore(date.minusDays(1))) && (toDate.isAfter(date))
        && (this.workDays.contains(date.getDayOfWeek()))) {
      logger.debug("This user is on date");
      return true;
    } else {
      logger.debug("This user is not on date");
      return false;
    }
  }

}
