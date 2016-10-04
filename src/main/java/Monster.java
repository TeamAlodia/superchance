// Replaced strength with power, updated all Function
// Updated functions for new database structure
// Set up constructor for species_id
// Add getters for new variables
// Organize methods
// Set equals up for full-state comparison, immediately used it to find out where you were missing vriables in your save/update
// Implemented DatabaseManagement


// NOTE: Seperate your logic further. Don't call updates internally, and especially not inconsistently. It's just begging for someone to forget which method they have to manually update with.

import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.Date;
import java.lang.Math;
import java.util.Random;

public class Monster implements DatabaseManagement{
  private int id;
  private int player_id;
  private int species_id;
  private String name;
  private Timestamp born;
  private Timestamp last_interacted;
  private boolean in_battle = false;
  private int exp = 0;
  private int level = 1;
  private boolean maxLevel = false;
  private int rest = 100;
  private int base_health = 100;
  private int base_power;
  private int base_defense;
  private int base_deck_size = 13;
  private int health = 10;
  private int power = 1;
  private int defense = 1;
  private int health_weight = 0;
  private int power_weight = 0;
  private int defense_weight = 0;
  private String status;

  public static final int MAX_LEVEL = 10;
  public static final int MAX_REST = 100;
  public static final int MAX_EXPERIENCE = 100;
  public static final int TRAINING_COST = 20;
  public static final int EXPERIENCE_FOR_LEVEL = 100;
  public static final int POINTS_GAINED_PER_LEVEL = 10;

  public Monster(int _player_id, String _name, int _species_id) {
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
  public int getSpecies_Id(){
    return species_id;
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
  public int getExp() {
    return exp;
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
  public int getBase_Power() {
    return base_power;
  }
  public int getBase_Defense() {
    return base_defense;
  }
  public int getBase_Deck_Size() {
    return base_deck_size;
  }
  public int getHealth() {
    return health;
  }
  public int getPower() {
    return power;
  }
  public int getDefense() {
    return defense;
  }
  public int getHealth_Weight() {
    return health_weight;
  }
  public int getPower_Weight() {
    return power_weight;
  }
  public int getDefense_Weight() {
    return defense_weight;
  }
  public String getStatus() {
    return status;
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
  public void setExp(int _exp) {
    exp = _exp;
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
  public void setPower(int _power) {
    power = _power;
  }
  public void setDefense(int _defense) {
    defense = _defense;
  }
  public void setHealth_Weight(int _health_weight) {
    health_weight = _health_weight;
  }
  public void setPower_Weight(int _power_weight) {
    power_weight = _power_weight;
  }
  public void setDefense_Weight(int _defense_weight) {
    defense_weight = _defense_weight;
  }

  // Database Functions
  @Override
  public boolean equals(Object otherMonster){
    if (!(otherMonster instanceof Monster))
      return false;
    Monster newMonster = (Monster) otherMonster;
    return id == newMonster.getId() &&
      species_id == newMonster.getSpecies_Id() &&
      name.equals(newMonster.getName()) &&
      born.equals(newMonster.getBorn()) &&
      last_interacted.equals(newMonster.getLast_Interacted()) &&
      in_battle == newMonster.getIn_Battle() &&
      exp == newMonster.getExp() &&
      level == newMonster.getLevel() &&
      rest == newMonster.getRest() &&
      base_health == newMonster.getBase_Health() &&
      base_power == newMonster.getBase_Power() &&
      base_defense == newMonster.getBase_Defense() &&
      base_deck_size == newMonster.getBase_Deck_Size() &&
      power == newMonster.getPower() &&
      defense == newMonster.getDefense() &&
      health_weight == newMonster.getHealth_Weight() &&
      power_weight == newMonster.getPower_Weight() &&
      defense_weight == newMonster.getPower_Weight();// &&
      // status.equals(newMonster.getStatus());
  }

  public void save() {
      try(Connection con = DB.sql2o.open()) {
        String sql = "INSERT INTO monsters (player_id, species_id, name, born, last_interacted, in_battle, exp, level, rest, base_health, base_power, base_defense, base_deck_size, health, power, defense, health_weight, power_weight, defense_weight, status) VALUES (:player_id, :species_id, :name, :born, :last_interacted, :in_battle, :exp, :level, :rest, :base_health, :base_power, :base_defense, :base_deck_size, :health, :power, :defense, :health_weight, :power_weight, :defense_weight, :status)";
        id = (int) con.createQuery(sql, true)
          .addParameter("player_id", player_id)
          .addParameter("species_id", species_id)
          .addParameter("name", name)
          .addParameter("born", born)
          .addParameter("last_interacted", last_interacted)
          .addParameter("in_battle", in_battle)
          .addParameter("exp", exp)
          .addParameter("level", level)
          .addParameter("rest", rest)
          .addParameter("base_health", base_health)
          .addParameter("base_power", base_power)
          .addParameter("base_defense", base_defense)
          .addParameter("base_deck_size", base_deck_size)
          .addParameter("health", health)
          .addParameter("power", power)
          .addParameter("defense", defense)
          .addParameter("health_weight", health_weight)
          .addParameter("power_weight", power_weight)
          .addParameter("defense_weight", defense_weight)
          .addParameter("status", status)
          .executeUpdate()
          .getKey();
      }
    }

  public void update() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE monsters SET player_id = :player_id, species_id = :species_id, name = :name, born = :born, last_interacted = :last_interacted, in_battle = :in_battle, exp = :exp, level = :level, rest = :rest, base_health = :base_health, base_power = :base_power, base_defense = :base_defense, base_deck_size = :base_deck_size, health = :health, power = :power, defense = :defense, health_weight = :health_weight, power_weight = :power_weight, defense_weight = :defense_weight, status = :status WHERE id = :id";

      con.createQuery(sql)
        .addParameter("player_id", player_id)
        .addParameter("species_id", species_id)
        .addParameter("name", name)
        .addParameter("born", born)
        .addParameter("last_interacted", last_interacted)
        .addParameter("in_battle", in_battle)
        .addParameter("exp", exp)
        .addParameter("level", level)
        .addParameter("rest", rest)
        .addParameter("base_health", base_health)
        .addParameter("base_power", base_power)
        .addParameter("base_defense", base_defense)
        .addParameter("base_deck_size", base_deck_size)
        .addParameter("health", health)
        .addParameter("power", power)
        .addParameter("defense", defense)
        .addParameter("health_weight", health_weight)
        .addParameter("power_weight", power_weight)
        .addParameter("defense_weight", defense_weight)
        .addParameter("status", status)
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

  public static List<Monster> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM monsters";
      List<Monster> allMonsters = con.createQuery(sql)
      .executeAndFetch(Monster.class);
      return allMonsters;
    }
  }

  public static Monster find(int _id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM monsters WHERE id=:id";

      return con.createQuery(sql)
        .addParameter("id", _id)
        .executeAndFetchFirst(Monster.class);
    }
  }

  // Increment Functions
  public boolean incrementExp(int _gain) {
    if(maxLevel == true) {
      return false;
    }
    // Increase experience by an amount determined by level
    exp += Math.ceil(_gain/level);
    // If new experience total is above the set experience required for level, then attempt level up. Return exception if at max level.
    if(exp >= EXPERIENCE_FOR_LEVEL) {
      try {
        this.incrementLevel(1);
        exp = exp - EXPERIENCE_FOR_LEVEL;
      } catch (UnsupportedOperationException exception) {
        maxLevel = true;
      }
    }
    this.update();
    return true;
  }

  public void incrementLevel(int _gain) {
    if (level >= MAX_LEVEL) {
      throw new UnsupportedOperationException("This monster has reached its full potential");
    }

    int totalGain = 0;
    int weightTotal = health_weight + defense_weight + power_weight;
    int healthGain = 0;
    int powerGain = 0;
    int defenseGain = 0;

    if(weightTotal > 0) {
      healthGain = (int)((health_weight / weightTotal) * POINTS_GAINED_PER_LEVEL * 10);
      powerGain = (int)((power_weight / weightTotal) * POINTS_GAINED_PER_LEVEL);
      defenseGain = (int)((defense_weight / weightTotal) * POINTS_GAINED_PER_LEVEL);
      totalGain = healthGain/10 + powerGain + defenseGain;
    }


    while(totalGain < POINTS_GAINED_PER_LEVEL) {
      Random random = new Random();
      int number = random.nextInt(3) + 1;
      if(number == 1) {
        healthGain += 10;
      } else if (number == 2) {
        powerGain += 1;
      } else {
        defenseGain += 1;
      }
      totalGain += 1;
    }

    base_health += healthGain;
    health += healthGain;
    power += powerGain;
    defense += defenseGain;
    base_deck_size += 3;
    health_weight = 0;
    defense_weight = 0;
    power_weight = 0;

    level += _gain;
  }

  public void incrementRest(int _gain) {
    if (rest >= MAX_REST) {
      throw new UnsupportedOperationException("This monster is fully rested");
    }
    rest += _gain;
  }

  // Decrement Functions
  public void decrementRest(int _loss) {
    if (rest <= 0) {
      throw new UnsupportedOperationException("This monster is completely exhausted");
    }
    rest -= _loss;
  }

  // Training Functions
  public int train() {
    Random random = new Random();
    int number = 0;
    try {
      number = random.nextInt(10) + 1;
      this.decrementRest(TRAINING_COST);
      boolean check = this.incrementExp(10);
    } catch (UnsupportedOperationException exception) {
      return 0;
    }
    return number;
  }

  public void trainPower() {
    power_weight += this.train();
    this.update();
  }

  public void trainDefense() {
    defense_weight += this.train();
    this.update();
  }

  public void trainHealth() {
    health_weight += this.train();
    this.update();
  }

}
