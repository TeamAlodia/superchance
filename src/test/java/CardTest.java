import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class CardTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void card_InstantiatesCorrectly(){
    Card firstCard = new Card();

    assertTrue(firstCard instanceof Card);
  }

  @Test
  public void equals_ComparesCorrectly_WhenEqual(){
    Card firstCard = new Card();
    Card secondCard = firstCard;

    assertEquals(firstCard, secondCard);
  }

  @Test
  public void save_SavesCorrectly(){
    Card firstCard = new Card();
    firstCard.save();

    assertEquals(firstCard, Card.find(firstCard.getId()));
  }

  @Test
  public void find_FindsCorrectly_SecondItem(){
    Card firstCard = new Card();
    firstCard.save();
    Card secondCard = new Card();
    secondCard.save();

    assertEquals(secondCard, Card.find(secondCard.getId()));
  }

  @Test
  public void all_ReturnsCorrectly_AllItems_TwoItems(){
    Card firstCard = new Card();
    firstCard.save();
    Card secondCard = new Card();
    secondCard.save();

    assertEquals(2, Card.all().size());
  }

  @Test
  public void all_ReturnsCorrectly_SecondItem(){
    Card firstCard = new Card();
    firstCard.save();
    Card secondCard = new Card();
    secondCard.save();

    assertEquals(secondCard, Card.all().get(1));
  }

  // @Test
  // public void update_UpdatesCorrectly_AllFields(){
  //   Card firstCard = new Card();
  //   firstCard.save();
  //
  //   firstCard.setName();
  //   firstCard.update();
  //
  //   Card updatedCard = Card.find(firstCard.getId());
  //
  //   assertEquals(, updatedCard.getName());
  // }

  @Test
  public void delete_DeletesCorrectly_FirstItem(){
    Card firstCard = new Card();
    firstCard.save();
    Card secondCard = new Card();
    secondCard.save();
    firstCard.delete();

    assertEquals(1, Card.all().size());
    assertEquals(null, Card.find(firstCard.getId()));
  }

}
