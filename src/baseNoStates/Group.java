package baseNoStates;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

// This class contains Users in a Group. Each group has determined permissons, access areas and Schedules.
public class Group {
    private String typeGroup;  // The name of this type of Group
    private ArrayList<User> users;  // The list of Users that belong to this Group. (who)
    private Schedule schedules;  // The determined schedules of the Users of this Group (when they can access).
    private ArrayList<String> actions;  // The determined actions that the Users of this Group can make (what).
    private ArrayList<Area> accesAreas;  // The determined areas that the Users of this Group can access (where).

    public Group(String name, Schedule setSchedule, ArrayList<String> operativeActions, ArrayList<Area> myArea) {
        typeGroup = name;
        schedules = setSchedule;
        actions = operativeActions;
        users = new ArrayList<>();
        accesAreas = myArea;
    }

    // =====================================================
    // ||              Setters and Getters                ||
    // =====================================================

    ArrayList<Area> getSpaces() {return accesAreas;}



    // =====================================================
    // ||           Other methods of this class           ||
    // =====================================================


    public boolean isOnUsers(User searchUser)
    { //Looking for user with the same credential and same name. If already exists then return true, otherwise false.
        for( User actualUser:users)
        {
            if(actualUser.getCredential().equals(searchUser.getCredential()) && actualUser.getName().equals(searchUser.getName())  ){
                return true;
            }
        }
        return false;
    }

    public User searchUser(String userCredential){ // Search for user with the same credential and returns it if its find (Instance of User), otherwise a null value will be returned.
        for( User actualUser:users)
        {
            if(userCredential.equals(actualUser.getCredential()))
            {
                return actualUser;
            }
        }
        System.out.println("The user with credential: " + userCredential + " has not been found");
        return null;
    }

    public void addUser(User newUser) // Add a User to the current userList of this Group.
    {
        // Check if the User is actually added. In this case, we won't add it to the list. Otherwise we will.
        if( !isOnUsers(newUser)) {
            users.add(newUser);
            System.out.println(newUser.toString() + " added succesfully");
        }
        else{
            System.out.println(newUser.toString() + " is already on the list");
        }
    }

// Method that returns true if the user have access to an Area (correct: Action, Date, Time, and current Area).
    public boolean hasAccessToArea(String areaToAccess,Area currentArea, LocalDate date, LocalTime hour, String action, ArrayList<String> reason) {
        boolean permissionConced = false;
        if(currentArea.getPartitionName().equals(areaToAccess)) {
            //In this if we check if the user can do the action required and if is on time and date
            if (this.actions.contains(action)) {
                if (this.schedules.isOnTime(hour)) {
                    if (this.schedules.isOnDate(date)) {
                        permissionConced = true;
                    } else {
                        reason.add("This user is not on date");
                    }
                } else {
                    reason.add("This user is not on hour");
                }
            } else {
                reason.add("This user can't do the action: " + action);
            }
        }
        //If we are in a Space class and does exist partition list it will return null.
        else if(currentArea.getAreaList() != null){

            for(Area actualArea : currentArea.getAreaList()){
                //General case: if reason is not empty and permissionConced is false, means that area has not been found
                if (reason.isEmpty() && !permissionConced)
                {
                    permissionConced = hasAccessToArea(areaToAccess, actualArea,  date,  hour, action, reason);
                }
                else{
                    break;
                }
            }
        }


        return permissionConced; // If true then the user have access to this area else, the user doesn't have access.
    }
}
