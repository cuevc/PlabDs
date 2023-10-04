package baseNoStates;

import java.lang.reflect.Type;
import java.util.List;

public class Group {
    private String typeGroup;
    private List<User> users;
    private Schedule schedules;
    private List<Actions> actions;

    public Group(String name) {
        typeGroup = name;

        if(typeGroup.equals("Admin") || typeGroup.equals("admin") || typeGroup.equals("ADMIN"))
        {
            schedules.setAdmin();
        }
        else {
            if (typeGroup.equals("Manager") || typeGroup.equals("manager") || typeGroup.equals("MANAGER")) {
                schedules.setManager();
            }
            else{
                if(typeGroup.equals("Employee") || typeGroup.equals("employee") || typeGroup.equals("EMPLOYEE")){
                    schedules.setEmployee();
                }
                else {
                    if(typeGroup.equals("Blank") || typeGroup.equals("blank") ||typeGroup.equals("BLANK")){
                        schedules.setBlank();
                    }
                }
            }
        }

    }
}
