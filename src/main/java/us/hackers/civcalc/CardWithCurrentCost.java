package us.hackers.civcalc;

import java.util.StringJoiner;

public class CardWithCurrentCost {

    private final CivilizationCard cardToPurchase;
    private final int cost;

    public CardWithCurrentCost(CivilizationCard cardToPurchase, int cost) {
        this.cardToPurchase = cardToPurchase;
        this.cost = cost;
    }

    public CivilizationCard getCardToPurchase() {
        return cardToPurchase;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", cardToPurchase.getName() + "[", "]")
                .add(""+cost)
                .toString();
    }
}
