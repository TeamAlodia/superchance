import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Ability {
  private int id;

  public int getId() {
    return id;
  }

  public static void action(int _id, int _targetId, int _value) {
    Monster target = Monster.find(_targetId);
    switch (_id) {
      // Change health of target for combat
      case 1:
        target.setHealth(target.getHealth() + _value);
        target.update();
        break;
      // Change defense of target for combat
      case 2:
        target.setDefense(target.getDefense() + _value);
        target.update();
        break;
      // Change defense of target for round
      case 3:
        target.setTemp_Defense(_value);
        target.update();
        break;
      // Change power of target for combat
      case 4:
        target.setPower(target.getPower() + _value);
        target.update();
        break;
      default:
        break;
    }
  }

  // Database Functions
  public static Ability find(int _id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM abilities WHERE id=:id";

      return con.createQuery(sql)
        .addParameter("id", _id)
        .executeAndFetchFirst(Ability.class);
    }
  }

  public static List<Ability> all(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM abilities";

      return con.createQuery(sql)
        .executeAndFetch(Ability.class);
    }
  }

  @Override
  public boolean equals(Object _otherObject){
    if(!(_otherObject instanceof Ability)){
      return false;
    }else{
      Ability newAbility = (Ability) _otherObject;
      return newAbility.getId() == this.id;
    }
  }
}
