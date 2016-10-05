import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  @Override
  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/superchance_test", null, null);
  }

  @Override
  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String sql ="";

      sql = "DELETE FROM monsters *;";
      con.createQuery(sql).executeUpdate();

      sql = "DELETE FROM players *;";
      con.createQuery(sql).executeUpdate();

      sql = "DELETE FROM species *;";
      con.createQuery(sql).executeUpdate();
    }
  }

}
