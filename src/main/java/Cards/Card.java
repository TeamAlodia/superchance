public abstract class Card{
  // Member Variables
  protected int id = 0;
  protected String name = CardName.NONE;
  protected String type = CardType.NONE;
  protected String element = Element.NONE;
  protected String monster = MonsterName.NONE;
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
}
