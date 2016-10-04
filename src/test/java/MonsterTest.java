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

  // Constructor Tests
  @Test
  public void monster_instantiatesNewMonster() {
    Monster firstMonster = new Monster(1, 1, "Rompy");
    assertTrue(firstMonster instanceof Monster);
  }

  // Getter Tests
  @Test
  public void getName_monsterInstantiatesWithName_Rompy() {
    Monster firstMonster = new Monster(1, 1, "Rompy");
    assertEquals("Rompy", firstMonster.getName());
  }

  @Test
  public void getBorn_monsterInstantiatesWithTimeCreated_Timestamp() {
    Monster firstMonster = new Monster(1, 1, "Rompy");
    int verifyTime = new Timestamp(new Date().getTime()).getDay();
    assertEquals(verifyTime, firstMonster.getBorn().getDay());
  }

  @Test
  public void getLastInteracted_monsterInstantiatesWithLastInteracted_Timestamp() {
    Monster firstMonster = new Monster(1, 1, "Rompy");
    int verifyTime = new Timestamp(new Date().getTime()).getDay();
    assertEquals(verifyTime, firstMonster.getLast_Interacted().getDay());
  }

  // Experience Tests
  @Test
  public void incrementexp_monsterIncreasesExp() {
    Monster firstMonster = new Monster(1, 1, "Rompy");
    firstMonster.incrementExp(3);
    assertEquals(3, firstMonster.getExp());
  }

  @Test
  public void incrementexp_monsterexpResetsOnLevel() {
    Monster firstMonster = new Monster(1, 1, "Rompy");
    firstMonster.incrementExp(101);
    assertEquals(1, firstMonster.getExp());
    assertEquals(2, firstMonster.getLevel());
  }

  @Test
  public void incrementLevel_monsterLevelIncreasesCorrectly() {
    Monster firstMonster = new Monster(1, 1, "Rompy");
    firstMonster.incrementLevel(3);
    assertEquals(4, firstMonster.getLevel());
  }


  // Rest Tests
  @Test
  public void incrementRest_monsterRestStaysBelow101() {
    Monster firstMonster = new Monster(1, 1, "Rompy");
    try {
      firstMonster.incrementRest(10);
    } catch (UnsupportedOperationException exception) {}
    assertEquals(100, firstMonster.getRest());
  }

  @Test
  public void incrementRest_monsterRestIncreasesCorrectly() {
    Monster firstMonster = new Monster(1, 1, "Rompy");
    firstMonster.decrementRest(20);
    firstMonster.incrementRest(10);
    assertEquals(90, firstMonster.getRest());
  }

  @Test
  public void decrementRest_monsterRestDecrementsCorrectly() {
    Monster firstMonster = new Monster(1, 1, "Rompy");
    firstMonster.decrementRest(20);
    assertEquals(80, firstMonster.getRest());
  }

  // Training Tests
  @Test
  public void train_returnsNumber() {
    Monster firstMonster = new Monster(1, 1, "Rompy");
    int test = firstMonster.train();
    boolean check = false;
    if(test < 11 && test > 0) {
      check = true;
    }
    assertEquals(true, check);
  }

  @Test
  public void trainPower_increasesPowerWeight() {
    Monster firstMonster = new Monster(1, 1, "Rompy");
    firstMonster.trainPower();
    assertTrue(firstMonster.getPower_Weight() > 0);
  }

  @Test
  public void trainDefense_increasesDefenseWeight() {
    Monster firstMonster = new Monster(1, 1, "Rompy");
    firstMonster.trainDefense();
    assertTrue(firstMonster.getDefense_Weight() > 0);
  }

  @Test
  public void trainHealth_increasesHealthWeight() {
    Monster firstMonster = new Monster(1, 1, "Rompy");
    firstMonster.trainHealth();
    assertTrue(firstMonster.getHealth_Weight() > 0);
  }

  @Test
  public void train_decreasesRest() {
    Monster firstMonster = new Monster(1, 1, "Rompy");
    firstMonster.trainHealth();
    assertEquals(firstMonster.getRest(), 80);
  }

  // Find Tests
  @Test
  public void all_returnsAllInstancesOfMonster_true() {
    Monster firstMonster = new Monster(1, 1, "Rompy");
    firstMonster.save();
    Monster secondMonster = new Monster(2, 2, "Sniffles");
    secondMonster.save();
    assertEquals(true, Monster.all().get(0).equals(firstMonster));
    assertEquals(true, Monster.all().get(1).equals(secondMonster));
  }

  // Database Tests
  @Test
  public void save_insertsObjectIntoDatabase_Monster() {
    Monster firstMonster = new Monster(1, 1, "Rompy");
    firstMonster.save();
    assertEquals(true, Monster.all().get(0).equals(firstMonster));
  }

  @Test
  public void save_assignsIdToObject() {
    Monster firstMonster = new Monster(1, 1, "Rompy");
    firstMonster.save();
    Monster savedMonster = Monster.all().get(0);
    assertEquals(firstMonster.getId(), savedMonster.getId());
  }

  @Test
  public void update_updatesMonsterStatsAppropriately() {
    Monster firstMonster = new Monster(1, 1, "Rompy");
    firstMonster.save();
    firstMonster.setHealth(5);
    firstMonster.update();
    assertEquals(5, Monster.find(firstMonster.getId()).getHealth());
  }
  @Test
  public void delete_deletesMonster_true() {
    Monster firstMonster = new Monster(1, 1, "Rompy");
    firstMonster.save();
    firstMonster.delete();
    assertEquals(0, Monster.all().size());
  }

  // Override Tests
  @Test
  public void equals_returnsTrueIfId_true() {
    Monster firstMonster = new Monster(1, 1, "Rompy");
    firstMonster.save();
    Monster testMonster = Monster.find(firstMonster.getId());
    assertTrue(testMonster.equals(firstMonster));
  }

}
