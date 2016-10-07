import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Player {
  // Database vars
  private int id;
  private String name;
  private int wins = 0;
  private int losses = 0;

  public Player(String _name){
    name = _name;
  }

  // Getters/Setters
  public int getId(){
    return id;
  }

  public String getName(){
    return name;
  }

  public int getWins(){
    return wins;
  }

  public int getLosses(){
    return losses;
  }

  public void setName(String _name){
    name = _name;
  }

  @Override
  public boolean equals(Object _otherObject){
    if(!(_otherObject instanceof Player)){
      return false;
    }else{
      Player otherObject = (Player) _otherObject;
      return id == otherObject.getId() &&
        name.equals(otherObject.getName()) &&
        wins == otherObject.getWins() &&
        losses == otherObject.getLosses();
    }
  }

  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO players (name, wins, losses) VALUES (:name, :wins, :losses)";

      id = (int) con.createQuery(sql, true)
        .addParameter("name", name)
        .addParameter("wins", wins)
        .addParameter("losses", losses)
        .executeUpdate()
        .getKey();
    }
  }

  public static Player find(int _id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM players WHERE id=:id";
      return con.createQuery(sql)
        .addParameter("id", _id)
        .executeAndFetchFirst(Player.class);
    }
  }

  public static List<Player> all(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM players";
      return con.createQuery(sql)
        .executeAndFetch(Player.class);
    }
  }

  public void update(){
    try(Connection con = DB.sql2o.open()){
      String sql = "UPDATE players SET name = :name, wins = :wins, losses = :losses WHERE id=:id";

      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("wins", wins)
        .addParameter("losses", losses)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete(){
    try(Connection con = DB.sql2o.open()){
      String sql = "DELETE FROM players WHERE id=:id";

      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void incrementWins(){
    ++wins;
  }

  public void incrementLosses(){
    ++losses;
  }
}
