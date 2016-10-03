public class Attack extends Card{

  // Cpnstructor
  public Attack(String _name, String _type, String _description){
    super(_name, _type, _description);
    type = CardType.ATTACK;
  }
}
