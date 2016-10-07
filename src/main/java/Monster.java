import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;
import java.util.Random;

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
  private int healthDelay = 0;

  // Local vars
  private int health;
  private int power;
  private int defense;
  private boolean advantage = false;
  private boolean alive = true;
  private String status;

  private List<Integer> deck;
  private List<Integer> hand;
  private List<Integer> discard;

  public Monster(){
    deck = new ArrayList<Integer>();
    hand = new ArrayList<Integer>();
    discard = new ArrayList<Integer>();
  }

  public Monster(int _species_id, String _name, int _player_id) {
    species_id = _species_id;
    name = _name;
    player_id = _player_id;


    deck = new ArrayList<Integer>();
    hand = new ArrayList<Integer>();
    discard = new ArrayList<Integer>();

    // Instantiate a species of the right type and populate remaining vars
    Species species = Species.find(_species_id);
    max_health = species.getMax_Health();
    base_power = species.getBase_Power();
    base_defense = species.getBase_Defense();
    species_name = species.getSpecies_Name();
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

  public int getHealthDelay(){
    return healthDelay;
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

  public void setHealthDelay(int _healthDelay) {
    healthDelay = _healthDelay;
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

  public static List<Monster> allByPlayer(int _player_id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM monsters WHERE player_id = :player_id";
      return con.createQuery(sql)
        .addParameter("player_id", _player_id)
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

  public void removeFromHand(int _card_id){
    int index = hand.indexOf(_card_id);
    discard.add(hand.get(index));
    hand.remove(index);
  }

  public void draw(){
    int handSize = hand.size();
    int deckSize = deck.size();
    int diff = 5 - handSize;

    for(int i = 0; i < diff; i++){
      Random myRandomGenerator = new Random();
      int random = myRandomGenerator.nextInt(deckSize);
      Integer catcher = deck.get(random);
      hand.add(deck.get(random));
      deck.remove(random);
      deckSize = deck.size();
    }
  }

  public void buildDeck(){

    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT card_id FROM species_cards WHERE species_id = :species_id";

      List<Integer> results = con.createQuery(sql)
        .addParameter("species_id", this.getSpecies_Id())
        .executeAndFetch(Integer.class);

      this.setDeck(results);

    }
  }

  public void shuffle(){
    deck.addAll(discard);
    discard.clear();
  }

  public void increaseHealth(int _healthAmt){
    health += _healthAmt;
    if(health > max_health)
      health = max_health;
  }

  public void increaseHealthDelay(int _healthAmt){
    healthDelay = _healthAmt;
  }

  public void decreaseHealth(int _healthAmt){
    health -= _healthAmt;

    if(health <= 0)
      alive = false;

  }

  public void increasePower(int _powerAmt){
    power += _powerAmt;
  }

  public void decreasePower(int _powerAmt){
    power -= _powerAmt;
    if(power < 6)
      power = 6;

  }

  public void increaseDefense(int _defenseAmt){
    defense += _defenseAmt;
  }

  public void decreaseDefense(int _defenseAmt){
    defense -= _defenseAmt;
    if(defense < 1)
      defense = 1;
  }

  public String getImage(int _index){
    return hand.get(_index) + ".png";
  }
}
