import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import java.util.Date;

public class DeckCreatorTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  // Constructor Tests
  @Test
  public void deckCreator_instantiatesNewDeckCreator() {
    DeckCreator firstDeckCreator = new DeckCreator();
    firstDeckCreator.save();
    assertTrue(firstDeckCreator instanceof DeckCreator);
  }
  //
  // @Test
  // public void save_SavesCorrectly() {
  //   // DeckCreator firstDeckCreator = new DeckCreator();
  //   firstDeckCreator.save();
  // }
}
