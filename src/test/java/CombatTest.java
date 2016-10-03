
import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;
import java.util.List;

public class CombatTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void combat_instantiatesNewCombat() {
    Combat combat = new Combat(1, 2);
    assertTrue(combat instanceof Combat);
  }

  @Test
  public void getFirstMonsterId_combatInstantiatesWith12() {
    Combat combat = new Combat(1, 2);
    assertEquals(1, combat.getFirstMonsterId());
  }

  @Test
  public void getSecondMonsterId_combatInstantiatesWith12() {
    Combat combat = new Combat(1, 2);
    assertEquals(2, combat.getSecondMonsterId());
  }

  @Test
  public void getFirstMonsterDone_combatInstantiatesWith12() {
    Combat combat = new Combat(1, 2);
    assertEquals(false, combat.getFirstMonsterDone());
  }

  @Test
  public void getSecondMonsterDone_combatInstantiatesWith12() {
    Combat combat = new Combat(1, 2);
    assertEquals(false, combat.getSecondMonsterDone());
  }


  @Test
  public void equals_returnsTrueIfID_true() {
    Combat firstCombat = new Combat(1, 2);
    Combat testCombat = Combat.find(firstCombat.getId());
    assertTrue(testCombat.equals(firstCombat));
  }


  @Test
  public void save_insertsObjectIntoDatabase_Combat() {
    Combat firstCombat = new Combat(1, 2);
    firstCombat.save();
    assertEquals(true, Combat.all().get(1).equals(firstCombat));
  }

  @Test
  public void all_returnsAllInstancesOfCombat_true() {
    Combat firstCombat = new Combat(1, 2);
    Combat secondCombat = new Combat(3, 4);
    assertEquals(true, Combat.all().get(0).equals(firstCombat));
    assertEquals(true, Combat.all().get(1).equals(secondCombat));
  }

  @Test
  public void save_assignsIdToObject() {
    Combat firstCombat = new Combat(1, 2);
    firstCombat.save();
    Combat savedCombat = Combat.all().get(1);
    assertEquals(firstCombat.getId(), savedCombat.getId());
  }

  @Test
  public void delete_deletesCombat_true() {
    Combat testCombat = new Combat(1, 2);
    testCombat.delete();
    assertEquals(0, Combat.all().size());
  }
}
