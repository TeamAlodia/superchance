import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import org.sql2o.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/howto", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/howto.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/players", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();

      model.put("players", Player.all());
      model.put("template", "templates/players.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/players/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int player_id = Integer.parseInt(request.params(":id"));

      // System.out.println(Player.all().size());
      model.put("monsters", Monster.allByPlayer(player_id));
      model.put("player", Player.find(player_id));
      model.put("template", "templates/player.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/monsters/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int monster = Integer.parseInt(request.params(":id"));

      model.put("monster", Monster.find(monster));
      model.put("template", "templates/monster.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/ready", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();

      model.put("players", Player.all());
      model.put("template", "templates/ready.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/set", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int playerOne = Integer.parseInt(request.queryParams("playerOne"));
      int playerTwo = Integer.parseInt(request.queryParams("playerTwo"));

      request.session().attribute("playerOne", playerOne);
      request.session().attribute("playerTwo", playerTwo);
      request.session().removeAttribute("battle");

      model.put("teamOne", Monster.allByPlayer(playerOne));
      model.put("teamTwo", Monster.allByPlayer(playerTwo));      model.put("template", "templates/set.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/battle", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Battle battle = request.session().attribute("battle");

      Monster monsterOne = new Monster();
      Monster monsterTwo = new Monster();
      String resolve = "Continue";
      int playedOne;
      int playedTwo;
      // If battle is starting for first time
      if(battle == null) {
        int playerOne = request.session().attribute("playerOne");
        int playerTwo = request.session().attribute("playerTwo");

        monsterOne = Monster.find(Integer.parseInt(request.queryParams("teamOne")));

        monsterTwo = Monster.find(Integer.parseInt(request.queryParams("teamTwo")));

        battle = new Battle(monsterOne, monsterTwo, playerOne, playerTwo);

        // System.out.println(monsterOne.getDeck().size());

        request.session().attribute("battle", battle);
      }
      // If battle is continuing
      else{
        monsterOne = battle.getPlayer_One_Monster();
        monsterTwo = battle.getPlayer_Two_Monster();
        // New Code

        if(!(request.queryParams("p1-input").equals(""))){
          int p1input = Integer.parseInt(request.queryParams("p1-input"));
          playedOne = monsterOne.getHand().get(p1input);
          monsterOne.removeFromHand(playedOne);
        } else {
          playedOne = 0;
        }

        if(!(request.queryParams("p2-input").equals(""))){
          int p2input = Integer.parseInt(request.queryParams("p2-input"));

          playedTwo = monsterTwo.getHand().get(p2input);
          monsterTwo.removeFromHand(playedTwo);
        } else {
          playedTwo = 0;
        }

        resolve = battle.resolveTurn(playedOne, playedTwo);
        model.put("resolve", resolve);
      }

      // If deck is 0, reshuffle
      if(monsterOne.getDeck().size() == 0) {
        monsterOne.shuffle();
      }
      if(monsterTwo.getDeck().size() == 0) {
        monsterTwo.shuffle();
      }

      // Bring hand up to 5
      monsterOne.draw();
      monsterTwo.draw();
      if(resolve.equals("Continue")) {
        model.put("monsterOne", monsterOne);
        model.put("monsterTwo", monsterTwo);
        model.put("handOne", monsterOne.getHand());
        model.put("handTwo", monsterTwo.getHand());
        model.put("template", "templates/battle.vtl");
      } else {
        battle = null;
        request.session().attribute("battle", battle);
        monsterOne.setHealth(monsterOne.getMax_Health());
        monsterTwo.setHealth(monsterTwo.getMax_Health());
        model.put("resolve", resolve);
        model.put("template", "templates/resolve.vtl");
      }

      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/players", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      Player newPlayer = new Player(name);
      newPlayer.save();

      String monster_name = request.queryParams("monster_name");
      Monster firstMonster = new Monster(1, monster_name, newPlayer.getId());
      firstMonster.save();

      response.redirect("/players");
      return "Player created";
    });
  }
}
