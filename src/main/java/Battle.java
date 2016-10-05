import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Battle {
  // Local vars
  private int id;
  private int player_one_id;
  private int player_two_id;

  private Monster player_one_monster;
  private Monster player_two_monster;

  private int player_one_card_id;
  private int player_two_card_id;

  private List<Integer> player_one_deck = new ArrayList<Integer>();
  private List<Integer> player_two_deck = new ArrayList<Integer>();

  private List<Integer> player_one_hand = new ArrayList<Integer>();
  private List<Integer> player_two_hand = new ArrayList<Integer>();

  private List<Integer> player_one_discard = new ArrayList<Integer>();
  private List<Integer> player_two_discard = new ArrayList<Integer>();

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
  // incrementRound
  // increase round by one
  //
  // resolveTurn()
  // set listening to false
  // run resolveStatus() for each monster
  // call executeAbilities() for both cards
  // run discard() for both cards
  // run getAlive() for both monsters
  // return winner, tie or continue string based on getAlive() results
  //
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
  //
  // discard()
  // takes in player number, card_id
  // discriminates by player number, setting temp versions of hand and discard
  // takes in player_hand, player_discard, card_id
  // removes first instance of card_id from player_hand
  // adds card_id to player_discard
  // discriminates by player number again, setting the appropriate vars from the temp vars
  //
  // shuffle()
  // takes in player number
  // discriminates by player number, setting temp versions of deck, hand and discard
  // sets all card_ids in hand to 0 and moves all cards from discard to deck
  // discriminates by player number again, setting the appropriate vars from the temp vars
  //
  // draw()
  // takes in player number
  // discriminates by player number, setting temp versions of deck, hand and discard
  // Removes all cards with id 0 from hand
  // Checks how many cards it takes to increase hand to 5
  // Checks if deck has that many cards
  // If it does, moves said amount of cards from draw to hand
  // If not, shuffle()
  // discriminates by player number again, setting the appropriate vars from the temp vars
  //
  // executeAbilities()
  // takes in card_id, active monster, passive monster
  // run down a switch case based on card_id
  // call static ability functions in appropriate case, with each returning a Monster
  // blank cards are 0 and should hit default and do nothing
}
