import java.util.List;
import org.sql2o.*;

public class Card{
  // Member Variables
  protected int id = 0;
  protected int element_id = 0;
  protected String name = CardName.NONE;
  protected String card_type = CardType.NONE;
  protected String description = "";
  protected String target = Target.NONE;

  // Constructors
  //// Placeholder
  public Card(){

  }
  //// No element -- falls back to default of 0
  public Card(String _name, String _type, String _description, String _target){
    name = _name;
    card_type = _type;
    description = _description;
    target = _target;
  }
  //// element
  public Card(String _name, String _type, String _description, String _target, int _elementId){
    name = _name;
    card_type = _type;
    description = _description;
    target = _target;
    element_id = _elementId;
  }

  // Getters
  public int getId(){
    return id;
  }
  public int getElementId(){
    return element_id;
  }
  public String getName(){
    return name;
  }
  public String getType(){
    return card_type;
  }
  public String getDescription(){
    return description;
  }

  // Setters
  public void setElementId(int _elementId){
    element_id = _elementId;
  }
  public void setName(String _name){
    name = _name;
  }
  public void setType(String _type){
    card_type = _type;
  }
  public void setDescription(String _description){
    description = _description;
  }

  // Overrides
  @Override
  public boolean equals(Object _other){
    if(!(_other instanceof Card))
      return false;
    Card newCard = (Card) _other;
    return id == newCard.id;
  }

  // CRUD
  //// Create
  public void create(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO cards (name, element_id, card_type, description, target) VALUES (:name, :element_id, :card_type, :description, :target)";
      id = (int) con.createQuery(sql,true)
        .addParameter("name",name)
        .addParameter("element_id",element_id)
        .addParameter("card_type",card_type)
        .addParameter("target", target)
        .addParameter("description",description)
        .executeUpdate()
        .getKey();
    }
  }

  //// Read
  public static List<Card> readAll(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM cards";
      return con.createQuery(sql)
        .executeAndFetch(Card.class);
    }
  }
  public static Card readById(int _id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM cards WHERE id = :id";
      return con.createQuery(sql)
        .addParameter("id",_id)
        .executeAndFetchFirst(Card.class);
    }
  }

  //// Update
  public void update(){
    try(Connection con = DB.sql2o.open()){
      String sql = "UPDATE cards SET element_id=:element_id, name=:name, card_type=:card_type, description=:description, target=:target WHERE id = :id";
      con.createQuery(sql)
        .addParameter("element_id",element_id)
        .addParameter("name",name)
        .addParameter("card_type",card_type)
        .addParameter("description",description)
        .addParameter("target", target)
        .addParameter("id",id)
        .executeAndFetchFirst(Card.class);
    }
  }

  //// Delete
  public void delete(){
    try(Connection con = DB.sql2o.open()){
      String sql = "DELETE FROM cards WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id",id)
        .executeUpdate();
    }
  }

}
