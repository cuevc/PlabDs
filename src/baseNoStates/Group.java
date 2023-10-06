package baseNoStates;

import java.lang.reflect.Type;
import java.util.List;

public class Group {
    private String typeGroup;
    private List<User> users;
    private Schedule schedules;
    private List<Actions> actions;

    public Group(String name, Schedule setSchedule, List<Actions> operativeActions) {
        typeGroup = name;
        schedules = setSchedule;
        actions = operativeActions;
    }
    public String getString() {return typeGroup;}
    public List<User> getUsers() {return users;}
    public Schedule getSchedules() {return schedules;}
    public List<Actions> getActions () {return actions;}

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
    public User searchUser(String userCredential, String userName){
        for( User actualUser:users)
        {
            if(actualUser.getCredential() == userCredential &&actualUser.getName() == userName ){
                return actualUser;
            }
        }
        System.out.println("The user: " + userName + " with credential: " + userCredential + " has not been found");
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
    public boolean hasAction(Actions searchedAction)
    {
        if(actions.contains(searchedAction)){
            return true;
        }
        return false;
    }
}
