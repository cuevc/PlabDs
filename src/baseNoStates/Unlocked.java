package baseNoStates;

public class Unlocked extends DoorState{
    @Override
    public void DoorSate(Door door){

    }
//gygygr
    @Override
    public void open(){
        if (door.getStateName() == States.UNLOCKED){
            // We can open the Door freely.
            if (door.isClosed()) {
                door.setClosed(false);

            }
        }
    }

    @Override
    public void close(){
        // We can close the Door freely.
        door.setClosed(true);
    }

    @Override
    public void lock(){
        if (door.isClosed()) {
            if (door.getStateName() == States.UNLOCKED) {
                // Just lock the door.
                door.setDoorState(new Locked());
            }
            else{
                // Door Already locked.
                System.out.println("Door already locked. Door: " + door.toString() + ".");
            }
        }
        else{
            // Can't lock the Door. The Door is open/propped.
            System.out.println("Door can't be locked. Door is open/propped: " + door.toString() + ".");
        }

    }
    @Override
    public void unlock(){
        if (door.getStateName()==States.LOCKED){
            // Unlock Door.
            door.setDoorState(new Unlocked());
        }
        else{
            // Error message. Door already unlocked.
            System.out.println("Door already unlocked. Door: " + door.toString() + ".");
        }
    }

}
