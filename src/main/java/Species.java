import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Species {
  // Database vars
  private int id;
  private String species_name = "";
  private int max_health;
  private int base_power;
  private int base_defense;

  public Species(String _name, int _max_health, int _base_power, int _base_defense){
    species_name = _name;
    max_health = _max_health;
    base_power = _base_power;
    base_defense = _base_defense;
  }

  public int getId(){
    return id;
  }

  public String getSpecies_Name(){
    return species_name;
  }

  public int getMax_Health(){
    return max_health;
  }

  public int getBase_Power(){
    return base_power;
  }

  public int getBase_Defense(){
    return base_defense;
  }

  public void setSpecies_Name(String _species_name){
    species_name = _species_name;
  }

  @Override
  public boolean equals(Object _otherObject){
    if(!(_otherObject instanceof Species)){
      return false;
    }else{
      Species otherObject = (Species) _otherObject;
      return id == otherObject.getId() &&
        species_name.equals(otherObject.getSpecies_Name()) &&
        max_health == otherObject.getMax_Health() &&
        base_power == otherObject.getBase_Power() &&
        base_defense == otherObject.getBase_Defense();
    }
  }

  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO species (species_name, max_health, base_power, base_defense) VALUES (:species_name, :max_health, :base_power, :base_defense)";

      id = (int) con.createQuery(sql, true)
        .addParameter("species_name", species_name)
        .addParameter("max_health", max_health)
        .addParameter("base_power", base_power)
        .addParameter("base_defense", base_defense)
        .executeUpdate()
        .getKey();
    }
  }

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

  public void update(){
    try(Connection con = DB.sql2o.open()){
      String sql = "UPDATE species_id = :species_name = :species_name, max_health = :max_health, base_power = :base_power, base_defense = :base_defense WHERE id=:id";

      con.createQuery(sql)
        .addParameter("species_name", species_name)
        .addParameter("max_health", max_health)
        .addParameter("base_power", base_power)
        .addParameter("base_defense", base_defense)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete(){
    try(Connection con = DB.sql2o.open()){
      String sql = "DELETE FROM species WHERE id=:id";

      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }
}
