package us.hackers.civcalc;

import java.util.Set;
import java.util.StringJoiner;

public class PossiblePurchase {
    private final Set<CardWithCurrentCost> cards;
    private final int costOfPurchase;
    private final int valueOfPurchase;

    public PossiblePurchase(Set<CardWithCurrentCost> cards, int costOfPurchase, int valueOfPurchase) {
        this.cards = cards;
        this.costOfPurchase = costOfPurchase;
        this.valueOfPurchase = valueOfPurchase;
    }

    public Set<CardWithCurrentCost> getCards() {
        return cards;
    }

    public int getCostOfPurchase() {
        return costOfPurchase;
    }

    public int getValueOfPurchase() {
        return valueOfPurchase;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PossiblePurchase.class.getSimpleName() + "[", "]\n")
                .add("cards=" + cards)
                .add("costOfPurchase=" + costOfPurchase)
                .add("valueOfPurchase=" + valueOfPurchase)
                .toString();
    }
}
