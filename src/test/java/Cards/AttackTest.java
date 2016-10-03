import java.util.List;
import java.util.ArrayList;
import org.junit.*;
import org.sql2o.*;
import static org.junit.Assert.*;

public class AttackTest {

  // Rules
  @Rule
  public DatabaseRule database = new DatabaseRule();

  // Tests
  //// Constructor
  @Test
  public void constructor_instantiatesCorrectly(){
    Attack testAttack = new Attack();
    assertEquals(true, testAttack instanceof Attack);
  }

  //// Read All
  @Test
  public void readAll_returnsAllCardsFromDB_ListCard(){
    Attack testAttack1 = new Attack();
    Attack testAttack2 = new Attack();
    testAttack1.create();
    testAttack2.create();
    List<Attack>expectedOutput = new ArrayList<>();
    expectedOutput.add(testAttack1);
    expectedOutput.add(testAttack2);
    assertEquals(expectedOutput, Attack.readAll());
  }

  //// Equals
  @Test
  public void equals_returnsFalseAppropriately_false(){
    Attack testAttack1 = new Attack();
    Attack testAttack2 = new Attack();
    testAttack1.create();
    testAttack2.create();
    assertEquals(false, testAttack1.equals(testAttack2));
  }
  @Test
  public void equals_returnsTrueAppropriately_true(){
    Attack testAttack1 = new Attack();
    Attack testAttack2 = testAttack1;
    testAttack1.create();
    testAttack2.create();
    assertEquals(true, testAttack1.equals(testAttack2));
  }

  //// Update
  @Test
  public void update_modifiesDBAppropriately_true(){
    Attack testAttack = new Attack();
    testAttack.create();
    testAttack.setName("Test Name");
    testAttack.update();
    assertEquals(testAttack.getName(), Attack.readById(testAttack.getId()).getName());
  }

  //// Delete
  @Test
  public void delete_removesFromDBAppropriately_true(){
    Attack testAttack = new Attack();
    testAttack.create();
    testAttack.delete();
    assertEquals(0, Attack.readAll().size());
  }
}
