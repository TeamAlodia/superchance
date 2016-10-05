// import org.sql2o.*;
// import org.junit.*;
// import static org.junit.Assert.*;
// import java.util.ArrayList;
// import java.util.List;
//
// public class MonsterTest {
//
//   @Rule
//   public DatabaseRule database = new DatabaseRule();
//
//   @Test
//   public void monster_InstantiatesCorrectly(){
//     Monster firstMonster = new Monster(1, "Bulbasaur");
//
//     assertTrue(firstMonster instanceof Monster);
//   }
//
//   @Test
//   public void monster_InstantiatesWithName(){
//     Monster firstMonster = new Monster(1, "Bulbasaur");
//
//     assertEquals("Bulbasaur", firstMonster.getName());
//   }
//
//   @Test
//   public void monster_InstantiatesWithSpecies_Id(){
//     Monster firstMonster = new Monster(1, "Bulbasaur");
//
//     assertEquals(1, firstMonster.getSpecies_Id());
//   }
//
//   @Test
//   public void monster_InstantiatesWithId_Serial(){
//     Monster firstMonster = new Monster(1, "Bulbasaur");
//     firstMonster.save();
//
//     assertTrue(firstMonster.getId() > 0);
//   }
//
//   @Test
//   public void equals_ComparesCorrectly_WhenEqual(){
//     Monster firstMonster = new Monster(1, "Bulbasaur");
//     Monster secondMonster = firstMonster;
//
//     assertEquals(firstMonster, secondMonster);
//   }
//
//   @Test
//   public void setDeck_SetsCorrectly_SecondItem(){
//     Monster firstMonster = new Monster(1, "Bulbasaur");
//     firstMonster.save();
//
//     List<Integer> firstDeck = new ArrayList<Integer>();
//     firstDeck.add(1);
//     firstDeck.add(2);
//
//     firstMonster.setDeck(firstDeck);
//
//     List<Integer> secondDeck = firstMonster.getDeck();
//
//     System.out.println(firstDeck.get(1));
//     System.out.println(secondDeck.get(1));
//
//     Integer expectedOutput = 2;
//     assertEquals(expectedOutput, secondDeck.get(1));
//   }
//
//   @Test
//   public void save_SavesCorrectly(){
//     Monster firstMonster = new Monster(1, "Bulbasaur");
//     firstMonster.save();
//
//     assertEquals(firstMonster, Monster.find(firstMonster.getId()));
//   }
//
//   @Test
//   public void find_FindsCorrectly_SecondItem(){
//     Monster firstMonster = new Monster(1, "Bulbasaur");
//     firstMonster.save();
//     Monster secondMonster = new Monster(2, "Charizard");
//     secondMonster.save();
//
//     assertEquals(secondMonster, Monster.find(secondMonster.getId()));
//   }
//
//   @Test
//   public void all_ReturnsCorrectly_AllItems_TwoItems(){
//     Monster firstMonster = new Monster(1, "Bulbasaur");
//     firstMonster.save();
//     Monster secondMonster = new Monster(2, "Charizard");
//     secondMonster.save();
//
//     assertEquals(2, Monster.all().size());
//   }
//
//   @Test
//   public void all_ReturnsCorrectly_SecondItem(){
//     Monster firstMonster = new Monster(1, "Bulbasaur");
//     firstMonster.save();
//     Monster secondMonster = new Monster(2, "Charizard");
//     secondMonster.save();
//
//     assertEquals(secondMonster, Monster.all().get(1));
//   }
//
//   @Test
//   public void update_UpdatesCorrectly_AllFields(){
//     Monster firstMonster = new Monster(1, "Bulbasaur");
//     firstMonster.save();
//
//     firstMonster.setName("Charizard");
//     firstMonster.update();
//
//     Monster updatedMonster = Monster.find(firstMonster.getId());
//
//     assertEquals("Charizard", updatedMonster.getName());
//   }
//
//   @Test
//   public void delete_DeletesCorrectly_FirstItem(){
//     Monster firstMonster = new Monster(1, "Bulbasaur");
//     firstMonster.save();
//     Monster secondMonster = new Monster(2, "Charizard");
//     secondMonster.save();
//     firstMonster.delete();
//
//     assertEquals(1, Monster.all().size());
//     assertEquals(null, Monster.find(firstMonster.getId()));
//   }
//
// }
