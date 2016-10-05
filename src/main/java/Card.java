import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Card {
  // Database vars
  private int id;
  private String name = "";
  private String description = "";
  private String type = "";

  private static final String TYPE_PHYSICAL = "physical";
  private static final String TYPE_SPECIAL = "special";
  private static final String TYPE_THROW = "throw";

  private static final String TYPE_BLOCK = "block";
  private static final String TYPE_SHIELD = "shield";
  private static final String TYPE_DODGE = "dodge";

  private static final String TYPE_OTHER = "other";

  public int getId(){
    return id;
  }

  public String getName(){
    return name;
  }

  public String getDescription(){
    return description;
  }

  public String getType(){
    return type;
  }

  public void setName(String _name){
    _name = _name;
  }

  @Override
  public boolean equals(Object _otherObject){
    if(!(_otherObject instanceof Card)){
      return false;
    }else{
      Card otherObject = (Card) _otherObject;
      return id == otherObject.getId() &&
        name.equals(otherObject.getName()) &&
        description.equals(otherObject.getDescription()) &&
        type.equals(otherObject.getType());
    }
  }

  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO cards (name, description, type) VALUES (:name, :description, :type)";

      id = (int) con.createQuery(sql, true)
        .addParameter("name", name)
        .addParameter("description", description)
        .addParameter("type", type)
        .executeUpdate()
        .getKey();
    }
  }

  public static Card find(int _id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM cards WHERE id=:id";
      return con.createQuery(sql)
        .addParameter("id", _id)
        .executeAndFetchFirst(Card.class);
    }
  }

  public static List<Card> all(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM cards";

      return con.createQuery(sql)
        .executeAndFetch(Card.class);
    }

  }

  public void update(){
    try(Connection con = DB.sql2o.open()){
      String sql = "UPDATE id = :name = :name, description = :description, type = :type WHERE id=:id";

      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("description", description)
        .addParameter("type", type)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete(){
    try(Connection con = DB.sql2o.open()){
      String sql = "DELETE FROM cards WHERE id=:id";

      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }
}
