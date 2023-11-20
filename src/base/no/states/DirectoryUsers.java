package base.no.states;

import door.state.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public final class DirectoryUsers {
  static Logger logger = LoggerFactory.getLogger("base.no.states.DirectoryUsers");

  private static final ArrayList<Group> rols = new ArrayList<>();

  public static void makeUsers() {

    // users without any privilege, just to keep temporally users instead of deleting them,
    // this is to withdraw all permissions but still to keep user data to give back
    // permissions later


    //Gets the root with all doors and areas, everything inside root!
    DirectoryAreas myAreas = new DirectoryAreas();
    myAreas.makeAreas();
    Area root = myAreas.getRootArea();


    // =====================================================
    // ||  Declare Users, Groups, Schedules and Actions   ||
    // =====================================================

    Group blank = new Group("Blank", null, null, null);
    blank.addUser(new User("Bernat", "12345", blank));

    rols.add(blank);

    // employees :
    // Sep. 1 2023 to Mar. 1 2024
    // week days 9-17h
    // just shortly unlock
    // ground floor, floor1, exterior, stairs (this, for all), that is, everywhere but the parking
    Schedule employeesSchedule = new Schedule(LocalDate.of(2023, 9, 1),
        LocalDate.of(2024, 3, 1), LocalTime.of(9, 0), LocalTime.of(21, 0),
        new ArrayList<>(Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY)));

    ArrayList<Area> employeeArea = new ArrayList<Area>();
    employeeArea.add(root.findPartitionById("ground_floor", root));
    employeeArea.add(root.findPartitionById("floor1", root));
    employeeArea.add(root.findPartitionById("exterior", root));
    employeeArea.add(root.findPartitionById("stairs", root));


    Group employees = new Group("Employees", employeesSchedule,
        new ArrayList<>(Arrays.asList(Actions.OPEN, Actions.CLOSE,
            Actions.UNLOCK_SHORTLY)), employeeArea);

    employees.addUser(new User("Eulalia", "43295", employees));

    rols.add(employees);


    // managers :
    // Sep. 1 2023 to Mar. 1 2024
    // week days + saturday, 8-20h
    // all actions
    // all spaces
    Schedule managersSchedule = new Schedule(LocalDate.of(2023, 9, 1),
        LocalDate.of(2024, 3, 1), LocalTime.of(8, 0), LocalTime.of(20, 0),
        new ArrayList<>(Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY)));

    ArrayList<Area> managersAreas = new ArrayList<Area>();
    managersAreas.add(root.findPartitionById("ground_floor", root));
    managersAreas.add(root.findPartitionById("floor1", root));
    managersAreas.add(root.findPartitionById("exterior", root));
    managersAreas.add(root.findPartitionById("stairs", root));
    managersAreas.add(root.findPartitionById("basement", root));

    Group managers = new Group("Manager", managersSchedule,
        new ArrayList<>(Arrays.asList(Actions.OPEN, Actions.CLOSE, Actions.LOCK,
            Actions.UNLOCK, Actions.UNLOCK_SHORTLY)), managersAreas);

    managers.addUser(new User("Manel", "95783", managers));
    managers.addUser(new User("Marta", "05827", managers));
    rols.add(managers);

    // admin :
    // always=2023 to 2100
    // all days of the week
    // all actions
    // all spaces

    Schedule adminSchedule = new Schedule(LocalDate.of(2023, 1, 1),
        LocalDate.of(2100, 1, 1), LocalTime.of(0, 0), LocalTime.of(23, 59),
        new ArrayList<>(Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY,
            DayOfWeek.SATURDAY, DayOfWeek.SUNDAY)));

    ArrayList<Area> adminArea = new ArrayList<Area>();

    Group admin = new Group("Admin", adminSchedule,
        new ArrayList<>(Arrays.asList(Actions.OPEN, Actions.CLOSE,
            Actions.LOCK, Actions.UNLOCK, Actions.UNLOCK_SHORTLY)), managersAreas);

    admin.addUser(new User("Ana", "11343", admin));
    rols.add(admin);

    logger.debug("All rols created and added to the rols attribute.");
  }

  // Find a User in any rol (group) by its Credential.
  public static User findUserByCredential(String credential) {

    for (Group aux : rols) {
      if (aux.searchUser(credential) != null) {
        logger.debug("findUserByCredential() -> The user with credential {} has been found", credential);
        return aux.searchUser(credential);
      }
    }
    logger.warn("The user with credential {} has not been found", credential);
    return null;
  }
}
