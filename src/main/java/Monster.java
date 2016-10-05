import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Monster {
  // Database vars
  private int id;
  private int player_id;
  private int species_id;
  private String species_name = "";
  private String name;
  private int max_health;
  private int base_power;
  private int base_defense;
  private int wins = 0;
  private int losses = 0;

  // Local vars
  private int health;
  private int power;
  private int defense;
  private boolean advantage = false;
  private boolean alive = true;
  private String status;

  private List<Integer> deck = new ArrayList<Integer>();

  private List<Integer> hand = new ArrayList<Integer>();

  private List<Integer> discard = new ArrayList<Integer>();

  public Monster(int _species_id, String _name){
    species_id = _species_id;
    name = _name;

    // Instantiate a species of the right type and populate remaining vars
  }


  // Getters/Setters
  public int getId(){
    return id;
  }

  public int getPlayer_Id(){
    return player_id;
  }

  public int getSpecies_Id(){
    return species_id;
  }

  public String getSpecies_Name(){
    return species_name;
  }

  public String getName(){
    return name;
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

  public int getWins(){
    return wins;
  }

  public int getLosses(){
    return losses;
  }

  public int getHealth(){
    return health;
  }

  public int getPower(){
    return power;
  }

  public int getDefense(){
    return defense;
  }

  public boolean getAdvantage(){
    return advantage;
  }

  public boolean getAlive(){
    return alive;
  }

  public String getStatus(){
    return status;
  }

  public List<Integer> getDeck(){
    return deck;
  }

  public List<Integer> getHand(){
    return hand;
  }

  public List<Integer> getDiscard(){
    return discard;
  }

  public void setName(String _name){
    name = _name;
  }

  public void incrementWins(){
    wins++;
  }

  public void incrementLosses(){
    losses++;
  }

  public void setHealth(int _health){
    health = _health;
  }

  public void setPower(int _power){
    power = _power;
  }

  public void setDefense(int _defense){
    defense = _defense;
  }

  public void setAdvantage(boolean _advantage){
    advantage = _advantage;
  }

  public void setAlive(boolean _alive){
    alive = _alive;
  }

  public void setStatus(String _status){
    status = _status;
  }

  public void setDeck(List<Integer> _deck){
    deck = _deck;
  }

  public void setHand(List<Integer> _hand){
    hand = _hand;
  }

  public void setDiscard(List<Integer> _discard){
    discard = _discard;
  }

  @Override
  public boolean equals(Object _otherObject){
    if(!(_otherObject instanceof Monster)){
      return false;
    }else{
      Monster otherObject = (Monster) _otherObject;
      return id == otherObject.getId() &&
        name.equals(otherObject.getName()) &&
        player_id == otherObject.getPlayer_Id() &&
        species_id == otherObject.getSpecies_Id() &&
        species_name.equals(otherObject.getSpecies_Name()) &&
        max_health == otherObject.getMax_Health() &&
        base_power == otherObject.getBase_Power() &&
        base_defense == otherObject.getBase_Defense() &&
        wins == otherObject.getWins() &&
        losses == otherObject.getLosses();
    }
  }

  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO monsters (player_id, species_id, species_name, name, max_health, base_power, base_defense, wins, losses) VALUES (:player_id, :species_id, :species_name, :name, :max_health, :base_power, :base_defense, :wins, :losses)";

      id = (int) con.createQuery(sql, true)
        .addParameter("player_id", player_id)
        .addParameter("species_id", species_id)
        .addParameter("species_name", species_name)
        .addParameter("name", name)
        .addParameter("max_health", max_health)
        .addParameter("base_power", base_power)
        .addParameter("base_defense", base_defense)
        .addParameter("wins", wins)
        .addParameter("losses", losses)
        .executeUpdate()
        .getKey();
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

  public static List<Monster> all(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM monsters";

      return con.createQuery(sql)
        .executeAndFetch(Monster.class);
    }

  }

  public void update(){
    try(Connection con = DB.sql2o.open()){
      String sql = "UPDATE monsters SET player_id = :player_id, species_id = :species_id, species_name = :species_name, name = :name, max_health = :max_health, base_power = :base_power, base_defense = :base_defense, wins = :wins, losses = :losses WHERE id=:id";

      con.createQuery(sql)
        .addParameter("player_id", player_id)
        .addParameter("species_id", species_id)
        .addParameter("species_name", species_name)
        .addParameter("name", name)
        .addParameter("max_health", max_health)
        .addParameter("base_power", base_power)
        .addParameter("base_defense", base_defense)
        .addParameter("wins", wins)
        .addParameter("losses", losses)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete(){
    try(Connection con = DB.sql2o.open()){
      String sql = "DELETE FROM monsters WHERE id=:id";

      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void removeFromDeck(int _card_id){
    
  }

  // increaseHealth
  // receives int
  // increases health by int
  // if over maximum, sets to maximum
  //
  // decreaseHealth
  // receives int
  // decreases health by int
  // set alive to false if health <= 0
  // if status is asleep or confused, sets to normal
  //
  // increaseStrength
  // receives int
  // increases strength by int
  //
  // decreaseStrength
  // receives int
  // decreases strength by int
  //
  // increaseDefense
  // receives int
  // increases defense by int
  //
  // decreaseDefense
  // receives int
  // decreases defense by int
  //
}
