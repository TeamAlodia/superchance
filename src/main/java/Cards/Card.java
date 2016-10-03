import java.util.List;
import org.sql2o.*;

public abstract class Card{
  // Member Variables
  protected int id = 0;
  protected int elementId = 0;
  protected String name = CardName.NONE;
  protected String type = CardType.NONE;
  protected String description = "";

  // Constructors
  //// No element -- falls back to default of 0
  public Card(String _name, String _type, String _description){
    name = _name;
    type = _type;
    description = _description;
  }
  //// element
  public Card(String _name, String _type, String _description, int _elementId){
    name = _name;
    type = _type;
    description = _description;
    elementId = _elementId;
  }

  // Getters
  public int getId(){
    return id;
  }
  public int getElementId(){
    return elementId;
  }
  public String getName(){
    return name;
  }
  public String getType(){
    return type;
  }
  public String getDescription(){
    return description;
  }

  // Setters
  public void setElementId(int _elementId){
    elementId = _elementId;
  }
  public void setName(String _name){
    name = _name;
  }
  public void setType(String _type){
    type = _type;
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
      String sql = "INSERT INTO cards (name, element_id, card_type, description) VALUES (:name, :elementId, :type, :description)";
      id = (int) con.createQuery(sql,true)
        .addParameter("name",name)
        .addParameter("elementId",elementId)
        .addParameter("type",type)
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
      String sql = "UPDATE cards SET element_id=:elementId, name=:name, card_type=:type, description=:description WHERE id = :id";
      con.createQuery(sql)
        .addParameter("elementId",elementId)
        .addParameter("name",name)
        .addParameter("type",type)
        .addParameter("description",description)
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
