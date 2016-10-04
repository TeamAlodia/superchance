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
  public void find_ReturnsCorrectly(){
    Species firstSpecies = new Species();
    firstSpecies.save();
    assertEquals(firstSpecies, Species.find(firstSpecies.getId()));
  }
}
