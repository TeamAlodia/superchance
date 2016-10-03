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

  // Find Functions
  public static List<Player> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM players";
      List<Player> allPlayers = con.createQuery(sql)
      .executeAndFetch(Player.class);
      return allPlayers;
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

  // Database Functions
  public void save() {
      try(Connection con = DB.sql2o.open()) {
        String sql = "INSERT INTO players (name, created, password) VALUES (:name, :created, :password)";
        id = (int) con.createQuery(sql, true)
          .addParameter("name", name)
          .addParameter("created", created)
          .addParameter("password", password)
          .executeUpdate()
          .getKey();
      }
    }

  public void update() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE players SET name = :name, created = :created, password = :password WHERE id = :id";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("created", created)
        .addParameter("password", password)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM players WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
      // String sql3 = "DELETE FROM cards_monsters_players where player_id = :id";
      // con.createQuery(sql3)
      //   .addParameter("id", id)
      //   .executeUpdate();
      // String sql2 = "DELETE FROM monsters WHERE player_id = :id";
      // con.createQuery(sql2)
      //   .addParameter("id", id)
      //   .executeUpdate();
    }
  }

  // Overrides
  @Override
  public boolean equals(Object otherPlayer){
    if (!(otherPlayer instanceof Player))
      return false;
    Player newPlayer = (Player) otherPlayer;
    return id == newPlayer.getId();
  }

}
