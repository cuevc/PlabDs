package base.no.states;

// Before executing enable assertions :
// https://se-education.org/guides/tutorials/intellijUsefulSettings.html

public class Main {
  public static void main(String[] args) {
    DirectoryUsers.makeUsers();
    DirectoryAreas.makeAreas();
    new WebServer();
  }
}
