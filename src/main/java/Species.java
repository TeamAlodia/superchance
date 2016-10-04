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
  public String getName(){
    return name;
  }
  public int getBase_Health(){
    return base_health;
  }
  public int getBase_Power(){
    return base_power;
  }
  public int getBase_Defense(){
    return base_defense;
  }

  // Database functions

  public static Species find(int _id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM species WHERE id=:id";

      return con.createQuery(sql)
        .addParameter("id", _id)
        .executeAndFetchFirst(Species.class);
    }
  }

  public static List<Species> all(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM species";

      return con.createQuery(sql)
        .executeAndFetch(Species.class);
    }
  }
}
