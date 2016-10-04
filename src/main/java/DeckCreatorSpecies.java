import org.sql2o.*;

public class DeckCreatorSpecies{
  public DeckCreatorSpecies(){
    Card attackBear = new Card("Maul", CardType.ATTACK, "Do 150% <power> (round up) in damage to opponent. Reduce defense of opponent by 20% (round up)", Target.OPPONENT, Element.BEAR);
    attackBear.create();
    Card defendBear = new Card("Thick Hide", CardType.DEFEND, "Increase <defense> by 20% (rounded up).", Target.SELF, Element.BEAR);
    defendBear.create();
    Card recoverBear = new Card("Hibernate", CardType.RECOVER, "Increase <defense> by 50% (rounded up). Increase <health> by 40% (rounded up).", Target.SELF, Element.BEAR);
    recoverBear.create();
    Card attackVizier = new Card("Find Weakness", CardType.ATTACK, "Do <power> in damage to opponent. Reduce enemy's <defense> by 20% (rounded up)", Target.OPPONENT, Element.VIZIER);
    attackVizier.create();
    Card defendVizier = new Card("Bodyguard", CardType.DEFEND, "Increase <health> by 50. Increase defense by 30% of <power>.", Target.SELF, Element.VIZIER);
    defendVizier.create();
    Card recoverVizier = new Card("Harem", CardType.RECOVER, "Increase health by <power>. Increase defense by 20% of power (rounded up). Increase power by 2", Target.SELF, Element.VIZIER);
    recoverVizier.create();
    Card attackClown = new Card("Creep Out", CardType.ATTACK, "Lower opponent's power by 30% of <power> (round up). Reduce enemy's <health> by 30% of power(rounded up)", Target.OPPONENT, Element.CLOWN);
    attackClown.create();
    Card defendClown = new Card("Lurk", CardType.DEFEND, "Increase <defense> by 50% of power (round up).", Target.BOTH, Element.CLOWN);
    defendClown.create();
    Card recoverClown = new Card("Blood of children", CardType.RECOVER, "Increase health by three times <power>. Decrease opponent's power by 20% (rounded up).", Target.BOTH, Element.CLOWN);
    recoverClown.create();

  }
}
