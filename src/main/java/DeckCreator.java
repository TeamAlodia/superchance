import java.util.List;
import org.sql2o.*;

public class DeckCreator{
  public DeckCreator(){
    // Make cards
    DeckCreatorNormal normalDeck = new DeckCreatorNormal();
    DeckCreatorType typeDeck = new DeckCreatorType();
    DeckCreatorSpecies speciesDeck = new DeckCreatorSpecies();
    // Make species
    Species bear = new Species("bear");
    Species vizier = new Species("vizier");
    Species clown = new Species("clown");
    bear.save();
    vizier.save();
    clown.save();

  }

  public void save() {
    List<Species> allSpecies = Species.all();
    List<Card> allNormalCards = Card.readAllNormal();
    List<Card> allTypeCards = Card.readAllTypeSpecific();

    try(Connection con = DB.sql2o.open()) {
      for(int i = 0; i < allSpecies.size(); ++i) {
        for(int j = 0; j <allNormalCards.size(); ++j) {
          String sql = "INSERT INTO species_potential_cards (card_id, species_id) VALUES (:card_id, :species_id)";
          con.createQuery(sql)
            .addParameter("species_id", allSpecies.get(i).getId())
            .addParameter("card_id", allNormalCards.get(j).getId())
            .executeUpdate();
        }
      }
    }
    //   for(int i = 0; i < allSpecies.size(); ++i) {
    //     for(int j = 0; j < allTypeCards.size(); ++j) {
    //       String sql = "INSERT INTO species_potential_cards SELECT card_id, species_id FROM species JOIN cards ON (species.element_id=cards.element_id)";
    //       con.createQuery(sql)
    //         .executeUpdate();
    //     }
    //   }
    // }
  }
}
