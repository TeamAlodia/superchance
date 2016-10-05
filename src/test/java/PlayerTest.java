import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void player_InstantiatesCorrectly(){
    Player firstPlayer = new Player("Ash");

    assertTrue(firstPlayer instanceof Player);
  }

  @Test
  public void player_InstantiatesWithName(){
    Player firstPlayer = new Player("Ash");

    assertEquals("Ash", firstPlayer.getName());
  }

  @Test
  public void equals_ComparesCorrectly_WhenEqual(){
    Player firstPlayer = new Player("Ash");
    Player secondPlayer = firstPlayer;

    assertEquals(firstPlayer, secondPlayer);
  }

  @Test
  public void save_SavesCorrectly(){
    Player firstPlayer = new Player("Ash");
    firstPlayer.save();

    assertEquals(firstPlayer, Player.find(firstPlayer.getId()));
  }

  @Test
  public void find_FindsCorrectly_SecondItem(){
    Player firstPlayer = new Player("Ash");
    firstPlayer.save();
    Player secondPlayer = new Player("Misty");
    secondPlayer.save();

    assertEquals(secondPlayer, Player.find(secondPlayer.getId()));
  }

  @Test
  public void all_ReturnsCorrectly_AllItems_TwoItems(){
    Player firstPlayer = new Player("Ash");
    firstPlayer.save();
    Player secondPlayer = new Player("Misty");
    secondPlayer.save();

    assertEquals(2, Player.all().size());
  }

  @Test
  public void all_ReturnsCorrectly_SecondItem(){
    Player firstPlayer = new Player("Ash");
    firstPlayer.save();
    Player secondPlayer = new Player("Misty");
    secondPlayer.save();

    assertEquals(secondPlayer, Player.all().get(1));
  }

  @Test
  public void update_UpdatesCorrectly_AllFields(){
    Player firstPlayer = new Player("Ash");
    firstPlayer.save();

    firstPlayer.setName("Misty");
    firstPlayer.update();

    Player updatedPlayer = Player.find(firstPlayer.getId());

    assertEquals("Misty", updatedPlayer.getName());
  }

  @Test
  public void delete_DeletesCorrectly_FirstItem(){
    Player firstPlayer = new Player("Ash");
    firstPlayer.save();
    Player secondPlayer = new Player("Misty");
    secondPlayer.save();
    firstPlayer.delete();

    assertEquals(1, Player.all().size());
    assertEquals(null, Player.find(firstPlayer.getId()));
  }

}
