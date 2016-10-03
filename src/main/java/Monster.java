import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.Date;

public class Monster {
  private int id;
  private int player_id;
  private String name;
  private Timestamp born;
  private Timestamp last_interacted;
  private boolean in_battle = false;
  private int experience = 0;
  private int level = 1;
  private int rest = 100;
  private int base_health = 100;
  private int base_deck_size = 13;
  private int health = 10;
  private int strength = 1;
  private int defense = 1;
  private int health_weight = 0;
  private int strength_weight = 0;
  private int defense_weight = 0;

  public static final int MAX_LEVEL = 10;
  public static final int MAX_REST = 100;

  public Monster(int _player_id, String _name) {
    player_id = _player_id;
    name = _name;
    born = new Timestamp(new Date().getTime());
    last_interacted = new Timestamp(new Date().getTime());
  }

  // Getters and Setters
  public int getId() {
    return id;
  }
  public int getPlayer_Id() {
    return player_id;
  }
  public String getName() {
    return name;
  }
  public Timestamp getBorn() {
    return born;
  }
  public Timestamp getLast_Interacted() {
    return last_interacted;
  }
  public boolean getIn_Battle() {
    return in_battle;
  }
  public int getExperience() {
    return experience;
  }
  public int getLevel() {
    return level;
  }
  public int getRest() {
    return rest;
  }
  public int getBase_Health() {
    return base_health;
  }
  public int getBase_Deck_Size() {
    return base_deck_size;
  }
  public int getHealth() {
    return health;
  }
  public int getStrength() {
    return strength;
  }
  public int getDefense() {
    return defense;
  }
  public int getHealth_Weight() {
    return health_weight;
  }
  public int getStrength_Weight() {
    return strength_weight;
  }
  public int getDefense_Weight() {
    return defense_weight;
  }

  public void setName(String _name) {
    name = _name;
  }
  public void setLast_Interacted(Timestamp _interacted) {
    last_interacted = _interacted;
  }
  public void setIn_Battle(boolean _battle) {
    in_battle = _battle;
  }
  public void setExperience(int _experience) {
    experience = _experience;
  }
  public void setLevel(int _level) {
    level = _level;
  }
  public void setRest(int _rest) {
    rest = _rest;
  }
  public void setBase_Health(int _base_health) {
    base_health = _base_health;
  }
  public void setBase_Deck_Size(int _base_deck_size) {
    base_deck_size = _base_deck_size;
  }
  public void setHealth(int _health) {
    health = _health;
  }
  public void setStrength(int _strength) {
    strength = _strength;
  }
  public void setDefense(int _defense) {
    defense = _defense;
  }
  public void setHealth_Weight(int _health_weight) {
    health_weight = _health_weight;
  }
  public void setStrength_Weight(int _strength_weight) {
    strength_weight = _strength_weight;
  }
  public void setDefense_Weight(int _defense_weight) {
    defense_weight = _defense_weight;
  }

  // Find Functions
  public static List<Monster> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM monsters";
      List<Monster> allMonsters = con.createQuery(sql)
      .executeAndFetch(Monster.class);
      return allMonsters;
    }
  }

  // Database Functions
  public void save() {
      try(Connection con = DB.sql2o.open()) {
        String sql = "INSERT INTO monsters (player_id, name, born, last_interacted, in_battle, experience, level, rest, base_health, base_deck_size, health, strength, defense, health_weight, strength_weight, defense_weight) VALUES (:player_id, :name, :born, :last_interacted, :in_battle, :experience, :level, :rest, :base_health, :base_deck_size, :health, :strength, :defense, :health_weight, :strength_weight, :defense_weight)";
        id = (int) con.createQuery(sql, true)
          .addParameter("player_id", player_id)
          .addParameter("name", name)
          .addParameter("born", born)
          .addParameter("last_interacted", last_interacted)
          .addParameter("in_battle", in_battle)
          .addParameter("experience", experience)
          .addParameter("level", level)
          .addParameter("rest", rest)
          .addParameter("base_health", base_health)
          .addParameter("base_deck_size", base_deck_size)
          .addParameter("health", health)
          .addParameter("strength", strength)
          .addParameter("defense", defense)
          .addParameter("health_weight", health_weight)
          .addParameter("strength_weight", strength_weight)
          .addParameter("defense_weight", defense_weight)
          .executeUpdate()
          .getKey();
      }
    }

  public void update() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE players SET player_id = :player_id, name = :name, born = :born, last_interacted = :last_interacted, in_battle = :in_battle, experience = :experience, level = :level, rest = :rest, base_health = :base_health, base_deck_size = :base_deck_size, health = :health, strength = :strength, defense = :defense, health_weight = :health_weight, strength_weight = :strength_weight, defense_weight = :defense_weight WHERE id = :id";
      con.createQuery(sql)
        .addParameter("player_id", player_id)
        .addParameter("name", name)
        .addParameter("born", born)
        .addParameter("last_interacted", last_interacted)
        .addParameter("in_battle", in_battle)
        .addParameter("experience", experience)
        .addParameter("level", level)
        .addParameter("rest", rest)
        .addParameter("base_health", base_health)
        .addParameter("base_deck_size", base_deck_size)
        .addParameter("health", health)
        .addParameter("strength", strength)
        .addParameter("defense", defense)
        .addParameter("health_weight", health_weight)
        .addParameter("strength_weight", strength_weight)
        .addParameter("defense_weight", defense_weight)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM monsters WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  // Overrides
  @Override
  public boolean equals(Object otherMonster){
    if (!(otherMonster instanceof Monster))
      return false;
    Monster newMonster = (Monster) otherMonster;
    return id == newMonster.getId();
  }

}
