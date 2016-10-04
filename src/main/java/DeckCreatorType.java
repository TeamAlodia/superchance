import org.sql2o.*;

public class DeckCreatorType{
  public DeckCreatorType(){
    Card attackViolent = new Card("Ravage", CardType.ATTACK, "Do twice <power> in damage to opponent. Reduce defense by half (rounded up)", Target.OPPONENT, Element.VIOLENCE);
    attackViolent.create();
    Card defendViolent = new Card("Enrage", CardType.DEFEND, "Increase <power> by 10% (rounded up). Increase <health> by 20% (rounded up). Reduce defense by 20% (rounded down)", Target.SELF, Element.VIOLENCE);
    defendViolent.create();
    Card recoverViolent = new Card("Feast", CardType.RECOVER, "Do <power> in damage to opponent. Increase <health> by 50% of <power> (rounded up)", Target.BOTH, Element.VIOLENCE);
    recoverViolent.create();
    Card attackDiplomacy = new Card("Blackmail", CardType.ATTACK, "Do <power> in damage to opponent. Reduce enemy's power by 20% (rounded up)", Target.OPPONENT, Element.DIPLOMACY);
    attackDiplomacy.create();
    Card defendDiplomacy = new Card("Evade", CardType.DEFEND, "Increase defense by <power>. Reduce enemy's power by 10% (rounded up)", Target.BOTH, Element.DIPLOMACY);
    defendDiplomacy.create();
    Card recoverDiplomacy = new Card("Opiates", CardType.RECOVER, "Increase health by <power>. Increase defense by 50% of power (rounded up)", Target.SELF, Element.DIPLOMACY);
    recoverDiplomacy.create();
    Card attackTrickery = new Card("Exploit", CardType.ATTACK, "Do <power> in damage to opponent. Reduce enemy's defense by 30% (rounded up)", Target.OPPONENT, Element.TRICKERY);
    attackTrickery.create();
    Card defendTrickery = new Card("Bait and Switch", CardType.DEFEND, "Reflect 20% of enemy's damage (rounded up) to enemy. Reduce rest of damage to 0.", Target.BOTH, Element.TRICKERY);
    defendTrickery.create();
    Card recoverTrickery = new Card("Feign Injury", CardType.RECOVER, "Increase health by <power>. Decrease opponent's power by 20% (rounded up). Reduce rest of damage to 0.", Target.BOTH, Element.TRICKERY);
    recoverTrickery.create();

  }
}
