public abstract class Card{
  // Member Variables
  protected int id = 0;
  protected int element_id = 0;
  protected String name = CardName.NONE;
  protected String type = CardType.NONE;
  protected String description = "";
  protected String location = CardLocation.DECK;

  // Constructor
  public Card(String _name, String _type, String _description){
    name = _name;
    type = _type;
    description = _description;
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
  public String getLocation(){
    return location;
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
  public void setLocation(String _location){
    location = _location;
  }

  // CRUD
  //// Create
  public void create(int _elementId){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO cards (name, elementId, type, description, location) VALUES (:name, :elementId, :type, :description, :location) WHERE id = :id";
      id = (int) con.createConnetion(sql,true)
        .executeUpdate()
        .addParameter("name",name)
        .addParameter("elementId",elementId)
        .addParameter("type",type)
        .addParameter("description",description)
        .addParameter("location",location)
        .addParameter("id",id)
        .getKey();
    }
  }

}
