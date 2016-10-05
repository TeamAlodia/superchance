import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Card {
  // Database vars
  private int id;
  private String name;
  private String description;
  private String type;

  private static final String TYPE_PHYSICAL = "physical";
  private static final String TYPE_SPECIAL = "special";
  private static final String TYPE_THROW = "throw";

  private static final String TYPE_BLOCK = "block";
  private static final String TYPE_SHIELD = "shield";
  private static final String TYPE_DODGE = "dodge";

  private static final String TYPE_OTHER = "other";
}
