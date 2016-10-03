import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import java.util.Date;

public class MonsterTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void monster_instantiatesNewMonster() {
    Monster firstMonster = new Monster(1, "Rompy");
    assertTrue(firstMonster instanceof Monster);
  }

  @Test
  public void getName_monsterInstantiatesWithName_Rompy() {
    Monster firstMonster = new Monster(1, "Rompy");
    assertEquals("Rompy", firstMonster.getName());
  }

  @Test
  public void getBorn_monsterInstantiatesWithTimeCreated_Timestamp() {
    Monster firstMonster = new Monster(1, "Rompy");
    int verifyTime = new Timestamp(new Date().getTime()).getDay();
    assertEquals(verifyTime, firstMonster.getBorn().getDay());
  }

  @Test
  public void getLastInteracted_monsterInstantiatesWithLastInteracted_Timestamp() {
    Monster firstMonster = new Monster(1, "Rompy");
    int verifyTime = new Timestamp(new Date().getTime()).getDay();
    assertEquals(verifyTime, firstMonster.getLast_Interacted().getDay());
  }

  @Test
  public void equals_returnsTrueIfNameAndEmailAreSame_true() {
    Monster firstMonster = new Monster(1, "Rompy");
    Monster testMonster = new Monster(1, "Rompy");
    assertTrue(testMonster.equals(firstMonster));
  }

  @Test
  public void save_insertsObjectIntoDatabase_Monster() {
    Monster firstMonster = new Monster(1, "Rompy");
    firstMonster.save();
    assertEquals(true, Monster.all().get(0).equals(firstMonster));
  }

  @Test
  public void all_returnsAllInstancesOfMonster_true() {
    Monster firstMonster = new Monster(1, "Rompy");
    firstMonster.save();
    Monster secondMonster = new Monster(2, "Sniffles");
    secondMonster.save();
    assertEquals(true, Monster.all().get(0).equals(firstMonster));
    assertEquals(true, Monster.all().get(1).equals(secondMonster));
  }

  @Test
  public void save_assignsIdToObject() {
    Monster firstMonster = new Monster(1, "Rompy");
    firstMonster.save();
    Monster savedMonster = Monster.all().get(0);
    assertEquals(firstMonster.getId(), savedMonster.getId());
  }

  @Test
  public void delete_deletesMonster_true() {
    Monster firstMonster = new Monster(1, "Rompy");
    firstMonster.save();
    firstMonster.delete();
    assertEquals(0, Monster.all().size());
  }
}
