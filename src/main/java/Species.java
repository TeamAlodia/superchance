import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Species {
  private int id;
  private String name;
  private int base_health;
  private int base_defense;
  private int base_power;

  // Getter/Setter functions
  public int getId(){
    return id;
  }
  public String name(){
    return name;
  }
  public int base_health(){
    return base_health;
  }
  public int base_power(){
    return base_power;
  }
  public int base_defense(){
    return base_defense;
  }

  // Database functions

  public Species find(_id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM species WHERE id=:id";

      return con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Species.class);
    }
  }
}
