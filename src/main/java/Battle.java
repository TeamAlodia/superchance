import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;
import java.util.Random;


public class Battle {
  // Local vars
  private int id;
  private int player_one_id;
  private int player_two_id;

  private Monster player_one_monster;
  private Monster player_two_monster;

  private int player_one_card_id;
  private int player_two_card_id;

  private int round = 0;
  private boolean listening = false;

  public static final String STATUS_NORMAL = "normal";

  public static final String STATUS_STUNNED = "stunned";
  public static final String STATUS_PARALYZED = "paralyzed";
  public static final String STATUS_ASLEEP = "asleep";

  public static final String STATUS_AFRAID = "afraid";
  public static final String STATUS_BERSERK = "berserk";
  public static final String STATUS_CONFUSED = "confused";

  public static final String STATUS_POISONED = "poisoned";

  //--Probably needs to be in App.java--
  // event handler for keypress
  // listens for keypress
  // if listening = true then set player_card_id to card_id of keypress
  //

  public Battle(Monster _player_one_monster, Monster _player_two_monster, int _player_one_id, int _player_two_id){
    player_one_monster = _player_one_monster;
    player_two_monster = _player_two_monster;
    player_one_id = _player_one_id;
    player_two_id = _player_two_id;
    player_one_monster.setHealth(player_one_monster.getMax_Health());
    player_two_monster.setHealth(player_two_monster.getMax_Health());
    player_one_monster.buildDeck();
    player_two_monster.buildDeck();

  }

  public Monster getPlayer_One_Monster(){
    return player_one_monster;
  }

  public Monster getPlayer_Two_Monster(){
    return player_two_monster;
  }

  public void incrementRound(){
    round++;
  }

  public String resolveTurn(int _player_one_card_id, int _player_two_card_id){
    player_one_card_id = _player_one_card_id;
    player_two_card_id = _player_two_card_id;

    // listening = false;

    // resolveStatus(player_one_monster, 1);
    // resolveStatus(player_two_monster, 2);

    // executeAbilities(player_one_card_id, player_two_card_id, player_one_monster, player_two_monster, 1);
    // executeAbilities(player_two_card_id, player_one_card_id, player_two_monster, player_one_monster, 2);
    //
    // discard(player_one_card_id);
    // discard(player_two_card_id);

    if(player_one_monster.getAlive() == player_two_monster.getAlive()){
      if(player_one_monster.getAlive()){
        return "Continue";
      }else {
        return "Draw";
      }
    }else if(player_one_monster.getAlive()){
      return "Player One";
    }else {
      return "Player Two";
    }

  }


  public void resolveStatus(Monster _activeMonster, int _activePlayer){

    Random myRandomGenerator = new Random();
    int random = myRandomGenerator.nextInt(10);

    switch (_activeMonster.getStatus()){
      case Battle.STATUS_STUNNED: player_one_card_id = 0;
        _activeMonster.setStatus(Battle.STATUS_NORMAL);
        break;
      case Battle.STATUS_PARALYZED: player_one_card_id = 0;
        if(random == 0){
          _activeMonster.setStatus(Battle.STATUS_NORMAL);
        }
        break;
      default: break;
    }
  }

  public void executeAbilities(int _active_card_id, int _passive_card_id, Monster _active_monster, Monster _passive_monster, int _activePlayer){

    switch (_active_card_id){
      case 1: break;
      case 2: break;
      case 3: break;
      case 4: break;
      case 5: break;
      case 6: break;
      case 7: break;
      case 8: break;
      case 9: break;
      case 10: break;
      default: break;
    }

  }
}

// resolveStatus()
// takes in player_monster
// run down switch case based on status of monster
//   normal proceeds as usual
//   stunned has card_id set to 0 and sets status to normal
//   paralyzed sets card_id to 0 and has a 50% chance to set to normal
//   asleep sets card_id to 0 and has a 25% chance to set to normal
//   afraid checks card_id to 0 if card type is an attack type and has a 50% chance to set to normal
//   berserk sets card_id to 0 if card type is a defense type and has a 50% chance to set to normal
//   confused sets card_id to a random card from the five positions and has a 25% chance to set to normal
//   poisoned deals 5 damage to monster and has a 50% chance to set to normal
