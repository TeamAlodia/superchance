
import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

/* Current Assumptions of how combat will work



*/
public class Combat {

  // Database attributes
  private int id;
  private int first_monster_id;
  private int second_monster_id;
  private boolean first_monster_done;
  private boolean second_monster_done;

  // Class attributes
  // private Monster first_monster;
  // private Monster second_monster;
  //List<Card> fm_cards;
  //List<Card> second_monster_cards;


  public Combat(int _first_monster_id, int _second_monster_id){
    first_monster_id = _first_monster_id;
    second_monster_id = _second_monster_id;
    first_monster_done = false;
    second_monster_done = false;

    this.save();
  }

  // Getters/Setters
  public int getId() {
    return id;
  }

  public int getFirst_monster_id() {
    return first_monster_id;
  }

  public int getSecond_monster_id() {
    return second_monster_id;
  }

  public boolean getFirst_monster_id() {
    return first_monster_done;
  }

  public boolean getSecond_monster_id() {
    return second_monster_done;
  }

  public void addCard(int _monster_id, int _card_id) {
    if(_monster_id == first_monster_id)
      first_monster_cards.add(Card.find(_card_id));
    else
      second_monster_cards.add(Card.find(_card_id));
  }

  // Database Methods
  @Override
  public boolean equals(Object _otherObject){
    if(!(_otherObject instanceof Combat)){
      return false;
    }else{
      Combat newCombat = (Combat) _otherObject;
      return newCombat.getId() == this.id;
    }
  }

  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO battle (first_monster_id, second_monster_id, first_monster_done, second_monster_done) VALUES (:first_monster_id, :second_monster_id, :first_monster_done, :second_monster_done)";
      id = (int) con.createQuery(sql, true)
        .addParameter("first_monster_id", first_monster_id)
        .addParameter("second_monster_id", second_monster_id)
        .addParameter("first_monster_done", first_monster_done)
        .addParameter("second_monster_done", second_monster_done)
        .executeUpdate()
        .getKey();
    }
  }

  public static Combat find(int _id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM battle WHERE id=:id";
      return con.createQuery(sql)
        .addParameter("id", _id)
        .executeAndFetchFirst(Combat.class);
    }
  }

  public static List<Combat> all(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM battle";
      return con.createQuery(sql).executeAndFetch(Combat.class);
    }
  }

  public void update(){
    try(Connection con = DB.sql2o.open()){
      String sql = "UPDATE battle SET (first_monster_id=:first_monster_id, second_monster_id=:second_monster_id, first_monster_done=:first_monster_done, second_monster_done=:second_monster_done) WHERE id=:id";
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
      String sql = "DELETE FROM battle WHERE id=:id";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }
  // Combat Methods
  public void attack(int _attacking_monster_id){
    private Monster attacking_monster = new Monster.find(_first_monster_id);
    private Monster defending_monster;
    if(first_monster_id != _attacking_monster_id)
      defending_monster = new Monster.find(first_monster_id);
    else
      defending_monster = new Monster.find(second_monster_id);
  }

  public void defend(){

  }
