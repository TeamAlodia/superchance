import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class BattleTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void battle_InstantiatesCorrectly(){
    Monster firstMonster = new Monster(1, "Bulbasaur");
    firstMonster.save();
    Monster secondMonster = new Monster(2, "Charizard");
    secondMonster.save();

    Battle firstBattle = new Battle(firstMonster, secondMonster, 1, 2);

    firstMonster.draw();
    firstMonster.removeFromHand(firstMonster.getHand().get(0));
    firstMonster.shuffle();

    assertTrue(firstBattle instanceof Battle);
  }



  // @Test
  // public void battle_InstantiatesWithName(){
  //   Battle firstBattle = new Battle(1, "Bulbasaur");
  //
  //   assertEquals("Bulbasaur", firstBattle.getName());
  // }
  //
  // @Test
  // public void battle_InstantiatesWithSpecies_Id(){
  //   Battle firstBattle = new Battle(1, "Bulbasaur");
  //
  //   assertEquals(1, firstBattle.getSpecies_Id());
  // }
  //
  // @Test
  // public void battle_InstantiatesWithId_Serial(){
  //   Battle firstBattle = new Battle(1, "Bulbasaur");
  //   firstBattle.save();
  //
  //   assertTrue(firstBattle.getId() > 0);
  // }
  //
  // @Test
  // public void equals_ComparesCorrectly_WhenEqual(){
  //   Battle firstBattle = new Battle(1, "Bulbasaur");
  //   Battle secondBattle = firstBattle;
  //
  //   assertEquals(firstBattle, secondBattle);
  // }
  //
  // @Test
  // public void setDeck_SetsCorrectly_SecondItem(){
  //   Battle firstBattle = new Battle(1, "Bulbasaur");
  //   firstBattle.save();
  //
  //   List<Integer> firstDeck = new ArrayList<Integer>();
  //   firstDeck.add(1);
  //   firstDeck.add(2);
  //
  //   firstBattle.setDeck(firstDeck);
  //
  //   List<Integer> secondDeck = firstBattle.getDeck();
  //
  //   System.out.println(firstDeck.get(1));
  //   System.out.println(secondDeck.get(1));
  //
  //   Integer expectedOutput = 2;
  //   assertEquals(expectedOutput, secondDeck.get(1));
  // }
  //
  // @Test
  // public void save_SavesCorrectly(){
  //   Battle firstBattle = new Battle(1, "Bulbasaur");
  //   firstBattle.save();
  //
  //   assertEquals(firstBattle, Battle.find(firstBattle.getId()));
  // }
  //
  // @Test
  // public void find_FindsCorrectly_SecondItem(){
  //   Battle firstBattle = new Battle(1, "Bulbasaur");
  //   firstBattle.save();
  //   Battle secondBattle = new Battle(2, "Charizard");
  //   secondBattle.save();
  //
  //   assertEquals(secondBattle, Battle.find(secondBattle.getId()));
  // }
  //
  // @Test
  // public void all_ReturnsCorrectly_AllItems_TwoItems(){
  //   Battle firstBattle = new Battle(1, "Bulbasaur");
  //   firstBattle.save();
  //   Battle secondBattle = new Battle(2, "Charizard");
  //   secondBattle.save();
  //
  //   assertEquals(2, Battle.all().size());
  // }
  //
  // @Test
  // public void all_ReturnsCorrectly_SecondItem(){
  //   Battle firstBattle = new Battle(1, "Bulbasaur");
  //   firstBattle.save();
  //   Battle secondBattle = new Battle(2, "Charizard");
  //   secondBattle.save();
  //
  //   assertEquals(secondBattle, Battle.all().get(1));
  // }
  //
  // @Test
  // public void update_UpdatesCorrectly_AllFields(){
  //   Battle firstBattle = new Battle(1, "Bulbasaur");
  //   firstBattle.save();
  //
  //   firstBattle.setName("Charizard");
  //   firstBattle.update();
  //
  //   Battle updatedBattle = Battle.find(firstBattle.getId());
  //
  //   assertEquals("Charizard", updatedBattle.getName());
  // }
  //
  // @Test
  // public void delete_DeletesCorrectly_FirstItem(){
  //   Battle firstBattle = new Battle(1, "Bulbasaur");
  //   firstBattle.save();
  //   Battle secondBattle = new Battle(2, "Charizard");
  //   secondBattle.save();
  //   firstBattle.delete();
  //
  //   assertEquals(1, Battle.all().size());
  //   assertEquals(null, Battle.find(firstBattle.getId()));
  // }

}
