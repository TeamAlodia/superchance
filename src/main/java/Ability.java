import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Ability {
  private int id;

  public int getId() {
    return id;
  }

  public static void action(int _id, int _targetId, int _value) {
    switch (_id) {
      // Change health of target for combat
      case 1:
        Monster.find(_targetId).setHealth(Monster.find(_targetId).getHealth() + _value);
        System.out.println(Monster.find(_targetId).getName());
        Monster.find(_targetId).update();
        System.out.println(Monster.find(_targetId).getHealth());
        break;
      // Change defense of target for combat
      case 2:
        Monster.find(_targetId).setDefense(Monster.find(_targetId).getDefense() + _value);
        break;
      // Change defense of target for round
      case 3:
        Monster.find(_targetId).setTemp_Defense(_value);
        break;
      // Change power of target for combat
      case 4:
        Monster.find(_targetId).setPower(Monster.find(_targetId).getPower() + _value);
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
