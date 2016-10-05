import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class SpeciesTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void species_InstantiatesCorrectly(){
    Species firstSpecies = new Species();

    assertTrue(firstSpecies instanceof Species);
  }

  @Test
  public void species_InstantiatesWithId_Serial(){
    Species firstSpecies = new Species();
    firstSpecies.save();

    assertTrue(firstSpecies.getId() > 0);
  }

  @Test
  public void equals_ComparesCorrectly_WhenEqual(){
    Species firstSpecies = new Species();
    Species secondSpecies = firstSpecies;

    assertEquals(firstSpecies, secondSpecies);
  }

  @Test
  public void save_SavesCorrectly(){
    Species firstSpecies = new Species();
    firstSpecies.save();

    assertEquals(firstSpecies, Species.find(firstSpecies.getId()));
  }

  @Test
  public void find_FindsCorrectly_SecondItem(){
    Species firstSpecies = new Species();
    firstSpecies.save();
    Species secondSpecies = new Species();
    secondSpecies.save();

    assertEquals(secondSpecies, Species.find(secondSpecies.getId()));
  }

  @Test
  public void all_ReturnsCorrectly_AllItems_TwoItems(){
    Species firstSpecies = new Species();
    firstSpecies.save();
    Species secondSpecies = new Species();
    secondSpecies.save();

    assertEquals(2, Species.all().size());
  }

  @Test
  public void all_ReturnsCorrectly_SecondItem(){
    Species firstSpecies = new Species();
    firstSpecies.save();
    Species secondSpecies = new Species();
    secondSpecies.save();

    assertEquals(secondSpecies, Species.all().get(1));
  }

  // @Test
  // public void update_UpdatesCorrectly_AllFields(){
  //   Species firstSpecies = new Species();
  //   firstSpecies.save();
  //
  //   firstSpecies.setName();
  //   firstSpecies.update();
  //
  //   Species updatedSpecies = Species.find(firstSpecies.getId());
  //
  //   assertEquals(, updatedSpecies.getName());
  // }

  @Test
  public void delete_DeletesCorrectly_FirstItem(){
    Species firstSpecies = new Species();
    firstSpecies.save();
    Species secondSpecies = new Species();
    secondSpecies.save();
    firstSpecies.delete();

    assertEquals(1, Species.all().size());
    assertEquals(null, Species.find(firstSpecies.getId()));
  }

}
