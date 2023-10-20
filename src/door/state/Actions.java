package door.state;


/**
 * The Actions class only contains constant attributes that refer to the actions that can be
 * performed.
 */
public final class Actions {
  // Possible actions in reader and area requests.
  public static final String LOCK = "lock";
  public static final String UNLOCK = "unlock";
  public static final String UNLOCK_SHORTLY = "unlock_shortly";
  // Possible actions in door requests.
  public static final String OPEN = "open";
  public static final String CLOSE = "close";
}
//manito no se hacer un commit