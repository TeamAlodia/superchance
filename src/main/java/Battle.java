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
    // listening = false;

    // resolveStatus(player_one_monster, 1);
    // resolveStatus(player_two_monster, 2);


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

  public int calcDamage(Monster _active_monster, Monster _passive_monster, int _bonus, double _advantage){
    return (int) Math.ceil((_active_monster.getPower() + _bonus) * _advantage) - _passive_monster.getDefense();
  }

  public void executeAbilities(Card _active_card, Card _passive_card, Monster _active_monster, Monster _passive_monster, int _activePlayer){

    switch (_active_card.getId()){
      case 1:
        if(!(_passive_card.getType().equals("block"))) {
          if(_passive_card.getType().equals("shield")) {
            System.out.println("Shielded, extra damage taken");
            System.out.println("Player One Power:" + _active_monster.getPower());
            System.out.println("Player Two Defense:" + _passive_monster.getDefense());

            int damage = this.calcDamage(_active_monster, _passive_monster, 1, 1.25);

            System.out.println("Damage done:" + damage);

            _passive_monster.decreaseHealth(damage);
          } else {
            System.out.println("Not blocked");
            System.out.println("Player One Power:" + _active_monster.getPower());
            System.out.println("Player Two Defense:" + _passive_monster.getDefense());
            int damage = this.calcDamage(_active_monster, _passive_monster, 1, 1);
            _passive_monster.decreaseHealth(damage);
          }
        }
        break;
      case 2:
        if(!(_passive_card.getType().equals("shield"))) {
          if(_passive_card.getType().equals("block")) {
            System.out.println("Shielded, extra damage taken");
            System.out.println("Player One Power:" + _active_monster.getPower());
            System.out.println("Player Two Defense:" + _passive_monster.getDefense());

            int damage = this.calcDamage(_active_monster, _passive_monster, 0, 1.25);

            System.out.println("Damage done:" + damage);

            _passive_monster.decreaseHealth(damage);
          } else {
            System.out.println("Not blocked");
            System.out.println("Player One Power:" + _active_monster.getPower());
            System.out.println("Player Two Defense:" + _passive_monster.getDefense());
            int damage = this.calcDamage(_active_monster, _passive_monster, 0, 1);
            _passive_monster.decreaseHealth(damage);
          }
        }
        break;
      case 3:
        if(_passive_card.getType().equals("dodge") || _passive_card.getType().equals("none")) {
          int damage = this.calcDamage(_active_monster, _passive_monster, 2, 2);
          _passive_monster.decreaseHealth(damage);
        }
        break;
      case 4: break;
      case 5: break;
      case 6: break;
      case 7:
        _active_monster.increaseHealth(15);
        break;
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
