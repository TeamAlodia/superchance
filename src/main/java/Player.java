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

  public void incrementWins(){
    wins++;
  }

  public void incrementLosses(){
    losses++;
  }



  // incrementWins
  // increases wins by one
  //
  // incrementLosses
  // increses losses by one

  // addMonster
  // takes in monster_id
  // adds entry into players_monsters
}
