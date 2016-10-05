import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;
import java.util.Random;

/* Battle pseudo code

// Pre Battle Work
1. Update IN_BATTLE = true for both monsters
2. Calculate HEALTH = base_health(REST/100)
3. POWER = base_power && DEFENSE = base_defense
4. Calculate Monster's DECK size based on Current Rest value
5  Retrieve all the card_ids for each monster and populate them into card_monsters as INACTIVE
6. Let players select their DECKs from card_monsters, mark as DECK
7. Select 5 cards at random from DECK and put in HAND


// Players Select Cards
For each Player:
1. If DECK size == 0, shuffle INACTIVE hards into DECK
2. Select card from DECK and put in HAND
3. Player selects card from HAND and set*MonsterDone(true)

// Battle Logic (if (first_monster_done == true && second_monster_done = true)
1. Select ABILITY from cards_abilities where cards_abilities.card_id == cards.id
2. ABILITY.action(cards_abilities.ability_id, card.targetId(), cards_abilities.value);
[update target monster](depends on how we want to seperate concerns)
3. ???
4. Update HEALTH for both monsters
5. Update card state to DISCARD
6. set*MonsterDone(false)

// Post Battle Work (if first_monster.HEALTH == 0 || second_monster.HEALTH ==0)
1. Determine Winner and Loser
2. Update Monsters:
  a. Winner gets 100% XP
  b. Loser gets 25% XP
  c. Both get -50% REST
  d. Both get LAST_INTERACTED update
  e. Update IN_BATTLE = false for both
3. Remove all cards from CARDS_MONSTERS
4. Trigger Winner/Loser UI

// ???
- Where to put the combat loop?

*/
public class Battle {

  // Database attributes
  private int id;
  private int first_monster_id;
  private int second_monster_id;
  private boolean first_monster_done;
  private boolean second_monster_done;

  public Battle(int _first_monster_id, int _second_monster_id){
    first_monster_id = _first_monster_id;
    second_monster_id = _second_monster_id;
    first_monster_done = false;
    second_monster_done = false;
  }

  // Getters/Setters
  public int getId() {
    return id;
  }

  public int getFirstMonsterId() {
    return first_monster_id;
  }

  public int getSecondMonsterId() {
    return second_monster_id;
  }

  public boolean getFirstMonsterDone() {
    return first_monster_done;
  }

  public boolean getSecondMonsterDone() {
    return second_monster_done;
  }

  public void setFirstMonsterDone(boolean _first_monster_done) {
    first_monster_done = _first_monster_done;
  }

  public void setSecondMonsterDone(boolean _second_monster_done) {
    second_monster_done = _second_monster_done;
  }

  // Database Methods
  @Override
  public boolean equals(Object _otherObject){
    if(!(_otherObject instanceof Battle)){
      return false;
    }else{
      Battle newBattle = (Battle) _otherObject;
      return newBattle.getId() == this.id;
    }
  }

  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO battles (first_monster_id, second_monster_id, first_monster_done, second_monster_done) VALUES (:first_monster_id, :second_monster_id, :first_monster_done, :second_monster_done)";
      id = (int) con.createQuery(sql, true)
        .addParameter("first_monster_id", first_monster_id)
        .addParameter("second_monster_id", second_monster_id)
        .addParameter("first_monster_done", first_monster_done)
        .addParameter("second_monster_done", second_monster_done)
        .executeUpdate()
        .getKey();
    }
  }

  public static Battle find(int _id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM battles WHERE id=:id";
      return con.createQuery(sql)
        .addParameter("id", _id)
        .executeAndFetchFirst(Battle.class);
    }
  }

  public static List<Battle> all(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM battles";
      return con.createQuery(sql).executeAndFetch(Battle.class);
    }
  }

  public void update(){
    try(Connection con = DB.sql2o.open()){
      String sql = "UPDATE battles SET (first_monster_id=:first_monster_id, second_monster_id=:second_monster_id, first_monster_done=:first_monster_done, second_monster_done=:second_monster_done) WHERE id=:id";
      con.createQuery(sql)
        .addParameter("first_monster_id", first_monster_id)
        .addParameter("second_monster_id", second_monster_id)
        .addParameter("first_monster_done", first_monster_done)
        .addParameter("second_monster_done", second_monster_done)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete(){
    try(Connection con = DB.sql2o.open()){
      String sql = "DELETE FROM battles WHERE id=:id";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

//----------------- Custom Battle Methods -------------

  public void initializeBattle(int _monster_id){

    deleteMonsterCards(_monster_id);

    Monster monster = Monster.find(_monster_id);

    int battleHealth = monster.getBase_Health() * (1/monster.getRest());

    monster.setIn_Battle(true);
    monster.setHealth(battleHealth);
    monster.setPower(monster.getBase_Power());
    monster.setDefense(monster.getBase_Defense());
    monster.save();

    List<Integer> monsterCards = getAllPotentialCardIds(_monster_id);

    // add all cards as inactive
    for(Integer card_id : monsterCards)
      addMonsterCard(card_id, _monster_id, CardStatus.INACTIVE);

  }

  public List<Integer> getAllPotentialCardIds(int _monster_id){
    Monster monster = Monster.find(_monster_id);
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT card_id FROM species_potential_cards WHERE species_id=:species_id";
      return con.createQuery(sql)
      .addParameter("species_id", monster.getSpecies_Id())
      .executeAndFetch(Integer.class);
    }
  }

  public int getDeckSize(int _monster_id){
    Monster monster = Monster.find(_monster_id);

    int deck_size = monster.getBase_Deck_Size() * (1/monster.getRest());

    return deck_size;
  }

  public void addMonsterCard(int _card_id, int _monster_id, String _card_type){
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO cards_monsters (card_id, monster_id, state) VALUES (:card_id, :monster_id, :state)";
      con.createQuery(sql, true)
        .addParameter("card_id", _card_id)
        .addParameter("monster_id", _monster_id)
        .addParameter("state", _card_type)
        .executeUpdate();
    }
  }

  public void updateMonsterCard(int _card_monsters_id , String _card_type){
    try(Connection con = DB.sql2o.open()){
      String sql = "UPDATE cards_monsters SET state=:state WHERE id=:id";
      con.createQuery(sql)
        .addParameter("state", _card_type)
        .addParameter("id", _card_monsters_id)
        .executeUpdate();
    }
  }

  public void getRandomDeckCard(int _monster_id) {

    // usable but prob not the best place for this:
    if(_monster_id == first_monster_id)
      setFirstMonsterDone(false);
    else
      setSecondMonsterDone(false);

    List<Integer> deckCards = getMonsterCards(_monster_id, CardStatus.DECK);

    //Reshuffle deck if depleated
    if(deckCards.isEmpty()) {
      deckCards = getMonsterCards(_monster_id, CardStatus.DISCARD);

      for(Integer card_id : deckCards)
         updateMonsterCard(card_id, CardStatus.DECK);

      deckCards = getMonsterCards(_monster_id, CardStatus.DECK);
    }

    //Select random card and put it in the hand
    int card_index = deckCards.get(randomInt(0, deckCards.size()-1));
    updateMonsterCard(card_index, CardStatus.HAND);

  }

  // marks card discarded and returns the card_id
  public int playCard(int _monster_id, int _card_monsters_id){

    if(_monster_id == first_monster_id)
      setFirstMonsterDone(true);
    else
      setSecondMonsterDone(true);

    // discard
    updateMonsterCard(_card_monsters_id, CardStatus.DISCARD);

    // get the card_id and return it
    return getCardID(_card_monsters_id);

  }

  public int getCardID(int _card_monsters_id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT card_id FROM cards_monsters WHERE id=:id";
      return con.createQuery(sql)
        .addParameter("id", _card_monsters_id)
        .executeAndFetchFirst(Integer.class);
    }
  }

  // returns a list of all of the ids for the given type
  public List<Integer> getMonsterCards(int _monster_id, String _card_type){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT id FROM cards_monsters WHERE monster_id=:monster_id AND state=:state";
      return con.createQuery(sql)
      .addParameter("monster_id", _monster_id)
      .addParameter("state", _card_type)
      .executeAndFetch(Integer.class);
    }
  }

  private int randomInt(int min, int max){
    Random rand = new Random();
    return(rand.nextInt(max - min)+min);
  }

  // clears out all monster's cards from cards_monsters
  public void deleteMonsterCards(int _monster_id){
    try(Connection con = DB.sql2o.open()){
      String sql = "DELETE FROM cards_monsters WHERE monster_id = :monster_id";
      con.createQuery(sql)
        .addParameter("monster_id", _monster_id)
        .executeUpdate();
    }
  }

}
