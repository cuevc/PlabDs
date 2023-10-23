package baseNoStates;

import java.lang.reflect.Type;
import java.security.PrivateKey;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Group {
    private String typeGroup;
    private ArrayList<User> users;
    private Schedule schedules;
    private ArrayList<String> actions;
    private ArrayList<Area> accesAreas;

    public Group(String name, Schedule setSchedule, ArrayList<String> operativeActions, ArrayList<Area> myArea) {
        typeGroup = name;
        schedules = setSchedule;
        actions = operativeActions;
        users = new ArrayList<>();
        accesAreas = myArea;
    }
    public String getTypeGroup() {return typeGroup;}
    public ArrayList<User> getUsers() {return users;}
    public Schedule getSchedules() {return schedules;}
    public ArrayList<String> getActions () {return actions;}

    public boolean isOnUsers(User searchUser)
    {
        //Looking for user with the same credential and same name
        //if already exists then return true, otherwise false.
        for( User actualUser:users)
        {
            if(actualUser.getCredential() == searchUser.getCredential() &&actualUser.getName() == searchUser.getName() ){
                return true;
            }
        }
        return false;
    }
    public User searchUser(String userCredential){
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
    public void addUser(User newUser)
    {
        //
        if( !isOnUsers(newUser)) {
            users.add(newUser);
            System.out.println(newUser.toString() + " added succesfully");
        }
        else{
            System.out.println(newUser.toString() + " is already on the list");
        }
    }
    public void removeUser(User deleteUser)
    {
        if(isOnUsers(deleteUser)){
            users.remove(deleteUser);
            System.out.println(deleteUser.toString() + " remove succesfully.");
        }
        else{
            System.out.println(deleteUser.toString() + " does not exist in this group.");

        }
    }
    public boolean hasAction(String act)
    {
        boolean hasIt  = false;
        if(actions != null){
            for(String Activision : actions){
                if(Activision.equals(act)){
                    hasIt = true;
                }
            }
        }

        return hasIt;
    }
    ArrayList<Area> getSpaces() {return accesAreas;}


    public boolean hasAccessToArea(String areaToAccess,Area currentArea, LocalDate date, LocalTime hour, String action, ArrayList<String> reason) {
        boolean permissionConced = false;
        if(currentArea.getPartition_name().equals(areaToAccess)) {
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
        //If we are in a space class and does exist partition list it will return null
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


        return permissionConced;
        //We check if the name is equal to recursive name node

        /* CÓDIGO POLILLA
        boolean[] printable = {false, false, false};
        printable[0] = !hasAction(action);
        printable[1] = !schedules.isOnDate(date) && schedules.isOnTime(hour);
        //printable[2] = !(accesAreas.get);
        return true; */
    }
}
