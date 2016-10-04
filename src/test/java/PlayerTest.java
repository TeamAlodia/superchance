// import org.sql2o.*;
// import org.junit.*;
// import static org.junit.Assert.*;
// import java.util.Arrays;
// import java.util.ArrayList;
// import java.util.List;
// import java.sql.Timestamp;
// import java.util.Date;
//
// public class PlayerTest {
//
//   @Rule
//   public DatabaseRule database = new DatabaseRule();
//
//   @Test
//   public void player_instantiatesNewPlayer() {
//     Player firstPlayer = new Player("Jordan", "Simple10");
//     assertTrue(firstPlayer instanceof Player);
//   }
//
//   @Test
//   public void getName_playerInstantiatesWithName_Jordan() {
//     Player firstPlayer = new Player("Jordan", "Simple10");
//     assertEquals("Jordan", firstPlayer.getName());
//   }
//
//   @Test
//   public void getCreated_playerInstantiatesWithTimeCreated_Timestamp() {
//     Player firstPlayer = new Player("Jordan", "Simple10");
//     int verifyTime = firstPlayer.getCreated().getDay();
//     assertEquals(verifyTime, firstPlayer.getCreated().getDay());
//   }
//
//   @Test
//   public void getPassword_playerInstantiatesWithPassword_Simple10() {
//     Player firstPlayer = new Player("Jordan", "Simple10");
//     assertEquals("Simple10", firstPlayer.getPassword());
//   }
//
//   @Test
//   public void equals_returnsTrueIfNameAndEmailAreSame_true() {
//     Player firstPlayer = new Player("Jordan", "Simple10");
//     Player testPlayer = new Player("Jordan", "Simple10");
//     assertTrue(testPlayer.equals(firstPlayer));
//   }
//
//   @Test
//   public void save_insertsObjectIntoDatabase_Player() {
//     Player firstPlayer = new Player("Jordan", "Simple10");
//     firstPlayer.save();
//     assertEquals(true, Player.all().get(0).equals(firstPlayer));
//   }
//
//   @Test
//   public void all_returnsAllInstancesOfPlayer_true() {
//     Player firstPlayer = new Player("Jordan", "Simple10");
//     firstPlayer.save();
//     Player secondPlayer = new Player("Pat", "Timple10");
//     secondPlayer.save();
//     assertEquals(true, Player.all().get(0).equals(firstPlayer));
//     assertEquals(true, Player.all().get(1).equals(secondPlayer));
//   }
//
//   @Test
//   public void save_assignsIdToObject() {
//     Player firstPlayer = new Player("Jordan", "Simple10");
//     firstPlayer.save();
//     Player savedPlayer = Player.all().get(0);
//     assertEquals(firstPlayer.getId(), savedPlayer.getId());
//   }
//
//   @Test
//   public void delete_deletesPlayer_true() {
//     Player testPlayer = new Player("Jordan", "Simple10");
//     testPlayer.save();
//     testPlayer.delete();
//     assertEquals(0, Player.all().size());
//   }
// }
