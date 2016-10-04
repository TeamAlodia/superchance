import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  @Override
  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/vponline_test", null, null);
  }

  @Override
  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deletePlayersQuery = "DELETE FROM players *;";
      String deleteMonstersQuery = "DELETE FROM monsters *;";
      String deleteBattlesQuery = "DELETE FROM battles *;";
      String deleteCardsQuery = "DELETE FROM cards *";
      String deleteSpeciesQuery = "DELETE FROM species *";
      con.createQuery(deletePlayersQuery).executeUpdate();
      con.createQuery(deleteMonstersQuery).executeUpdate();
      con.createQuery(deleteBattlesQuery).executeUpdate();
      con.createQuery(deleteCardsQuery).executeUpdate();
      con.createQuery(deleteSpeciesQuery).executeUpdate();
    }
  }

}
