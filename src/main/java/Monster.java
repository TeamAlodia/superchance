import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Monster {
  // Database vars
  private int id;
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
