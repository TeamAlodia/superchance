// import java.util.List;
// import java.util.ArrayList;
// import org.junit.*;
// import org.sql2o.*;
// import static org.junit.Assert.*;
//
// public class CardTest {
//
//   // Rules
//   @Rule
//   public DatabaseRule database = new DatabaseRule();
//
//   // Tests
//   //// Constructor
//   @Test
//   public void constructor_instantiatesCorrectly(){
//     Card testAttack = new Card();
//     assertEquals(true, testAttack instanceof Card);
//   }
//
//   //// Read All
//   @Test
//   public void readAll_returnsAllCardsFromDB_ListCard(){
//     Card testAttack1 = new Card();
//     Card testAttack2 = new Card();
//     testAttack1.create();
//     testAttack2.create();
//     List<Card>expectedOutput = new ArrayList<>();
//     expectedOutput.add(testAttack1);
//     expectedOutput.add(testAttack2);
//     assertEquals(expectedOutput, Card.readAll());
//   }
//
//   //// Equals
//   @Test
//   public void equals_returnsFalseAppropriately_false(){
//     Card testAttack1 = new Card();
//     Card testAttack2 = new Card();
//     testAttack1.create();
//     testAttack2.create();
//     assertEquals(false, testAttack1.equals(testAttack2));
//   }
//   @Test
//   public void equals_returnsTrueAppropriately_true(){
//     Card testAttack1 = new Card();
//     Card testAttack2 = testAttack1;
//     testAttack1.create();
//     testAttack2.create();
//     assertEquals(true, testAttack1.equals(testAttack2));
//   }
//
//   //// Update
//   @Test
//   public void update_modifiesDBAppropriately_true(){
//     Card testAttack = new Card();
//     testAttack.create();
//     testAttack.setName("Test Name");
//     testAttack.update();
//     assertEquals(testAttack.getName(), Card.readById(testAttack.getId()).getName());
//   }
//
//   //// Delete
//   @Test
//   public void delete_removesFromDBAppropriately_true(){
//     Card testAttack = new Card();
//     testAttack.create();
//     testAttack.delete();
//     assertEquals(0, Card.readAll().size());
//   }
//
//   @Test
//   public void readAllNormal_returnsAllNormalCards() {
//     Card firstCard = new Card("Slap", "Attack", "Slap a puppy", "Self");
//     Card secondCard = new Card("Sap", "Attack", "Slap a kitten", "Self");
//     Card thirdCard = new Card("Sip", "Attack", "Slap a kitten", "Self", 1);
//     firstCard.create();
//     secondCard.create();
//     thirdCard.create();
//     assertEquals(2, Card.readAllNormal().size());
//   }
// }
