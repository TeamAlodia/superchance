import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import org.sql2o.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";
    ProcessBuilder process = new ProcessBuilder();
     Integer port;
     if (process.environment().get("PORT") != null) {
         port = Integer.parseInt(process.environment().get("PORT"));
     } else {
         port = 4567;
     }

    setPort(port);

    get("/test",(request,response)->{
      Map<String,Object> model = new HashMap<>();

      // OrcDeckCreator testDeck = new OrcDeckCreator();
      // DeckCreator realDeck = new DeckCreator();

      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    },new VelocityTemplateEngine());

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/monsters", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();

      model.put("players", Player.all());
      model.put("allSpecies", Species.all());
      model.put("monsters", Monster.all());
      model.put("template", "templates/monsters.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/monsters/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int monster_id = Integer.parseInt(request.params(":id"));
      Monster monster = Monster.find(monster_id);

      model.put("player", Player.find(monster.getPlayer_Id()));
      model.put("monster", monster);
      model.put("template", "templates/monster.vtl");
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

      model.put("player", Player.find(player_id));
      model.put("template", "templates/player.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/species", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();

      model.put("allSpecies", Species.all());
      model.put("template", "templates/allspecies.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/species/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int species_id = Integer.parseInt(request.params(":id"));

      model.put("species", Species.find(species_id));
      model.put("template", "templates/species.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
    //-------------------------------------------------------

    post("/players", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      String password = request.queryParams("password");
      String password_confirm = request.queryParams("password_confirm");

      if(password.equals(password_confirm)){
        Player player = new Player(name, password);
        player.save();

        response.redirect("/players");
        return "Player created";
      } else {
        response.redirect("/players");
        return "Player not created";
      }
    });

    post("/monsters", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      int player_id = Integer.parseInt(request.queryParams("player_id"));
      int species_id = Integer.parseInt(request.queryParams("species_id"));
      String name = request.queryParams("name");

      Monster monster = new Monster(player_id, species_id, name);
      monster.save();

      response.redirect("/monsters");
      return "Monster created";
    });
  }
}
