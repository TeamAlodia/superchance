import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

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

  public Battle(Monster _player_one_monster, Monster _player_two_monster, int _player_one_id, int _player_two_id){
    player_one_monster = _player_one_monster;
    player_two_monster = _player_two_monster;
    player_one_id = _player_one_id;
    player_two_id = _player_two_id;

    player_one_monster.setHealth(player_one_monster.getMax_Health());
    player_two_monster.setHealth(player_two_monster.getMax_Health());

    player_one_monster.setPower(player_one_monster.getBase_Power());
    player_two_monster.setPower(player_two_monster.getBase_Power());

    player_one_monster.setDefense(player_one_monster.getBase_Defense());
    player_two_monster.setDefense(player_two_monster.getBase_Defense());

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
    Card player_one_card;
    Card player_two_card;
    if(player_one_card_id != 0) {
      player_one_card = Card.find(player_one_card_id);
    } else {
      player_one_card =  new Card(0, "none");
    }
    if(player_two_card_id != 0) {
      player_two_card = Card.find(player_two_card_id);
    } else {
      player_two_card = new Card(0, "none");
    }

    if(player_one_card_id != 0) {
      executeAbilities(player_one_card, player_two_card, player_one_monster, player_two_monster, 1);
    }
    if(player_two_card_id != 0) {
      executeAbilities(player_two_card, player_one_card, player_two_monster, player_one_monster, 2);
    }

    if(player_one_monster.getAlive() == player_two_monster.getAlive()){
      if(player_one_monster.getAlive()){
        if(player_one_monster.getHealthDelay() > 0) {
          player_one_monster.increaseHealth(player_one_monster.getHealthDelay());
          player_one_monster.setHealthDelay(0);
        }
        if(player_two_monster.getHealthDelay() > 0) {
          player_two_monster.increaseHealth(player_two_monster.getHealthDelay());
          player_two_monster.setHealthDelay(0);
        }
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

  public int calcDamage(Monster _active_monster, Monster _passive_monster, int _power_bonus, int _defense_bonus, double _advantage){
    return (int) Math.ceil((_active_monster.getPower() + _power_bonus) * _advantage) - _passive_monster.getDefense() - _defense_bonus;
  }

  public void executeAbilities(Card _active_card, Card _passive_card, Monster _active_monster, Monster _passive_monster, int _activePlayer){

    switch (_active_card.getId()){
      case 1:
        if(!(_passive_card.getType().equals("block"))) {
          if(_passive_card.getType().equals("shield")) {
            int damage = this.calcDamage(_active_monster, _passive_monster, _active_card.getPower_Bonus(), _passive_card.getDefense_Bonus(), 1.25);
            _passive_monster.decreaseHealth(damage);
          } else {
            int damage = this.calcDamage(_active_monster, _passive_monster, _active_card.getPower_Bonus(), _passive_card.getDefense_Bonus(), 1);
            _passive_monster.decreaseHealth(damage);
          }
        }
        break;
      case 2:
        if(!(_passive_card.getType().equals("shield"))) {
          if(_passive_card.getType().equals("block")) {
            int damage = this.calcDamage(_active_monster, _passive_monster, _active_card.getPower_Bonus(), _passive_card.getDefense_Bonus(), 1.25);
            _passive_monster.decreaseHealth(damage);
          } else {
            int damage = this.calcDamage(_active_monster, _passive_monster, _active_card.getPower_Bonus(), _passive_card.getDefense_Bonus(), 1);
            _passive_monster.decreaseHealth(damage);
          }
        }
        break;
      case 3:
        if(_passive_card.getType().equals("dodge") || _passive_card.getType().equals("none") || _passive_card.getType().equals("other")) {
          int damage = this.calcDamage(_active_monster, _passive_monster, _active_card.getPower_Bonus(), _passive_card.getDefense_Bonus(), 2);
          _passive_monster.decreaseHealth(damage);
        }
        break;
      case 4: break;
      case 5: break;
      case 6: break;
      case 7:
        _active_monster.increaseHealthDelay(15);
        break;
      case 8:
        _active_monster.increasePower(2);
        break;
      case 9:
        _passive_monster.decreaseDefense(1);
        break;
      case 10:
        if(!(_passive_card.getType().equals("block"))) {
          if(_passive_card.getType().equals("shield")) {
            int damage = this.calcDamage(_active_monster, _passive_monster, _active_card.getPower_Bonus(), _passive_card.getDefense_Bonus(), 1.25);
            _passive_monster.decreaseHealth(damage);
          } else {
            int damage = this.calcDamage(_active_monster, _passive_monster, _active_card.getPower_Bonus(), _passive_card.getDefense_Bonus(), 1);
            _passive_monster.decreaseHealth(damage);
          }
        }

        if(!(_passive_card.getType().equals("shield"))) {
          if(_passive_card.getType().equals("block")) {
            int damage = this.calcDamage(_active_monster, _passive_monster, _active_card.getPower_Bonus(), _passive_card.getDefense_Bonus(), 1.25);
            _passive_monster.decreaseHealth(damage);
          } else {
            int damage = this.calcDamage(_active_monster, _passive_monster, _active_card.getPower_Bonus(), _passive_card.getDefense_Bonus(), 1);
            _passive_monster.decreaseHealth(damage);
          }
        }
        break;
      default:
        break;
    }

  }
}
