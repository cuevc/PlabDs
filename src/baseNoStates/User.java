package baseNoStates;

import java.util.ArrayList;

public class User {
  private final String name;
  private final String credential;
  private Group adjecentGroup;

  public User(String name, String credential, Group groupies) {
    this.name = name;
    this.credential = credential;
    this.adjecentGroup = groupies;
  }

  public String getCredential() {
    return credential;
  }

  public Boolean hasPermision(String act){
    Boolean aux = adjecentGroup.hasAction(act);
    return aux;
  }

  public String getName() { return name;}
  @Override
  public String toString() {
    return "User{name=" + name + ", credential=" + credential + "}";
  }

}
