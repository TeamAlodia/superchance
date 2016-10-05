import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Monster {
  // Database vars
  private int id;
  private int player_id;
  private int species_id;
  private String species_name;
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
  // incrementWins
  // increases wins by one
  //
  // incrementLosses
  // increses losses by one
}
