import java.util.List;
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

}
