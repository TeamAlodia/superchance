import org.sql2o.*;

public class OrcDeckCreator{
  public OrcDeckCreator(){
    // Create Cards
    Card attackBasic = new Card(CardName.ATTACK, CardType.ATTACK, "Dmg = Base Power", Target.OPPONENT);
    attackBasic.create();

    Card defendBasic = new Card(CardName.DEFEND, CardType.DEFEND, "Prevent Dmg = Base Defense", Target.SELF);
    defendBasic.create();

    Card recoverBasic = new Card(CardName.RECOVER, CardType.RECOVER, "Recover HP = Power*Defense", Target.SELF);
    recoverBasic.create();

    // Create Player
    Player orcPlayer = new Player("orcPlayer","password");
    orcPlayer.save();

    // Create Monster
    Monster orcusMcBorcus = new Monster(orcPlayer.getId(), 1, "Orcus McBorcus");
    orcusMcBorcus.save();

    // Clear Deck to prevent duplication
    try(Connection con = DB.sql2o.open()){
      String clearDeck = "DELETE FROM species_potential_cards WHERE id=:id";
      con.createQuery(clearDeck)
        .addParameter("id",orcusMcBorcus.getId())
        .executeUpdate();
    }

    // Create Deck For Monster from Cards
    try(Connection con = DB.sql2o.open()){
      String addCard = "INSERT INTO species_potential_cards (card_id, species_id) VALUES (:card_id, :species_id)";
      for(int i = 0; i < 15; i++){
        con.createQuery(addCard,true)
          .addParameter("card_id",attackBasic.getId())
          .addParameter("species_id",orcusMcBorcus.getId())
          .executeUpdate();
      }
      for(int i = 0; i < 15; i++){
        con.createQuery(addCard,true)
          .addParameter("card_id",defendBasic.getId())
          .addParameter("species_id",orcusMcBorcus.getId())
          .executeUpdate();
      }
      for(int i = 0; i < 10; i++){
        con.createQuery(addCard,true)
          .addParameter("card_id",recoverBasic.getId())
          .addParameter("species_id",orcusMcBorcus.getId())
          .executeUpdate();
      }
    }
  }
}
