// import org.sql2o.*;
// import org.junit.*;
// import static org.junit.Assert.*;
// import java.util.Arrays;
// import java.util.ArrayList;
// import java.util.List;
//
// public class AbilityTest {
//
//   @Rule
//   public DatabaseRule database = new DatabaseRule();
//
//   @Test
//   public void action_executeActionOnTarget() {
//     Monster jim = new Monster(1, 2, "Jim");
//     jim.save();
//     Monster jack = new Monster(2, 2, "Jack");
//     jack.save();
//     Ability.action(1, jack.getId(), 3);
//     assertEquals(13, Monster.find(jack.getId()).getHealth());
//   }
// }
