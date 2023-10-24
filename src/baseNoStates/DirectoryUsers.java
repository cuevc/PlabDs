package baseNoStates;
import doorState.Actions;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public final class DirectoryUsers {

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

    Group Blank = new Group("Blank",null,null,null);
    Blank.addUser(new User("Bernat", "12345", Blank));

    rols.add(Blank);

    // employees :
    // Sep. 1 2023 to Mar. 1 2024
    // week days 9-17h
    // just shortly unlock
    // ground floor, floor1, exterior, stairs (this, for all), that is, everywhere but the parking
    Schedule employeesSchedule = new Schedule(LocalDate.of(2023,9,1),
        LocalDate.of(2024,3,1), LocalTime.of(9,0), LocalTime.of(21,0),
        new ArrayList<>(Arrays.asList(DayOfWeek.MONDAY,DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY)));

    ArrayList<Area> employeeArea = new ArrayList<Area>();
    employeeArea.add(root.findPartitionById("ground_floor",root));
    employeeArea.add(root.findPartitionById("floor1",root));
    employeeArea.add(root.findPartitionById("exterior",root));
    employeeArea.add(root.findPartitionById("stairs",root));



    Group employees = new Group("Employees", employeesSchedule, new ArrayList<>(Arrays.asList (Actions.OPEN, Actions.CLOSE, Actions.UNLOCK_SHORTLY)),employeeArea);

    employees.addUser(new User("Eulalia","43295", employees));

    rols.add(employees);


    // managers :
    // Sep. 1 2023 to Mar. 1 2024
    // week days + saturday, 8-20h
    // all actions
    // all spaces
    Schedule managersSchedule = new Schedule(LocalDate.of(2023,9,1),
        LocalDate.of(2024,3,1), LocalTime.of(8,0), LocalTime.of(20,0),
        new ArrayList<>(Arrays.asList(DayOfWeek.MONDAY,DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY)));

    ArrayList<Area> managersAreas = new ArrayList<Area>();
    managersAreas.add(root.findPartitionById("ground_floor",root));
    managersAreas.add(root.findPartitionById("floor1",root));
    managersAreas.add(root.findPartitionById("exterior",root));
    managersAreas.add(root.findPartitionById("stairs",root));
    managersAreas.add(root.findPartitionById("basement", root));

    Group managers = new Group("Manager", managersSchedule, new ArrayList<>(Arrays.asList (Actions.OPEN, Actions.CLOSE,Actions.LOCK, Actions.UNLOCK, Actions.UNLOCK_SHORTLY)), managersAreas);
    managers.addUser(new User("Manel", "95783",managers));
    managers.addUser(new User("Marta", "05827",managers));
    rols.add(managers);

    // admin :
    // always=2023 to 2100
    // all days of the week
    // all actions
    // all spaces

    Schedule AdminSchedule = new Schedule(LocalDate.of(2023,1,1),
        LocalDate.of(2100,1,1), LocalTime.of(0,0), LocalTime.of(23,59),
        new ArrayList<>(Arrays.asList(DayOfWeek.MONDAY,DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY,DayOfWeek.SUNDAY)));

    ArrayList<Area> adminArea = new ArrayList<Area>();

    Group Admin = new Group("Admin", AdminSchedule, new ArrayList<>(Arrays.asList (Actions.OPEN, Actions.CLOSE,Actions.LOCK, Actions.UNLOCK,Actions.UNLOCK_SHORTLY)),managersAreas);
    Admin.addUser(new User("Ana", "11343",Admin));
    rols.add(Admin);
  }


  public static User findUserByCredential(String credential) {  // Find a User in any rol (group) by its Credential.

    for (Group aux : rols) {
      if (aux.searchUser(credential) != null)
      {
        return aux.searchUser(credential);
      }
    }
    return null;
  }
}
