import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class MonsterTest {

  @Test
  public void monster_InstantiatesCorrectly(){
    Monster firstMonster = new Monster(1, "Bulbasaur");

    assertTrue(firstMonster instanceof Monster);
  }

  @Test
  public void monster_InstantiatesWithName(){
    Monster firstMonster = new Monster(1, "Bulbasaur");

    assertEquals("Bulbasaur", firstMonster.getName());
  }

  @Test
  public void monster_InstantiatesWithSpecies_Id(){
    Monster firstMonster = new Monster(1, "Bulbasaur");

    assertEquals(1, firstMonster.getSpecies_Id());
  }

  @Test
  public void equals_ComparesCorrectly_WhenEqual(){
    Monster firstMonster = new Monster(1, "Bulbasaur");
    Monster secondMonster = firstMonster;

    assertEquals(firstMonster, secondMonster);
  }


}
