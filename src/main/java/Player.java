import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.Date;

public class Player {
  private int id;
  private String name;
  private Timestamp created;
  private String password;

  public Player(String _name, String _password) {
    name = _name;
    created = new Timestamp(new Date().getTime());
    password = _password;
  }

  // Getters and Setters
  public int getId() {
    return id;
  }
  public String getName() {
    return name;
  }
  public Timestamp getCreated() {
    return created;
  }
  public String getPassword() {
    return password;
  }

  public void setName(String _name) {
    name = _name;
  }
  public void setPassword(String _password) {
    password = _password;
  }

}
