package baseNoStates;

public class Unlocked extends DoorState {

    public  Unlocked(Door door){
        doorAttr=door;
        door.setClosed(true);
    }

    @Override
    public void open(){
        if (doorAttr.getStateName().equals(States.UNLOCKED)){
            // We can open the Door freely.
            if (doorAttr.isClosed()) {
                doorAttr.setClosed(false);
            }
        }
    }

    @Override
    public void close(){
        // We can close the Door freely.
        doorAttr.setClosed(true);
    }

    @Override
    public void lock(){

        //only if Door is closed, it could be locked
        if (doorAttr.isClosed()) {

            System.out.println("Door locked");

        }
        else{
            // Can't lock the Door. The Door is open/propped.
            System.out.println("Door can't be locked. Door is open/propped");
        }

    }
    @Override
    public void unlock(){
        System.out.println("Door already unlocked");

    }

    @Override
    public void propped() {
        doorAttr.setDoorState(new Propped(doorAttr));
        System.out.println("Door Propped");

    }

}
