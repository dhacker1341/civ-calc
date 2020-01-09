package us.hackers.civcalc.advanced;

public class CardDiscount {
    private final String cardName;
    private final int discount;

    public CardDiscount(AdvancedCivilizationCard card, int discount) {
        this.cardName = card.getCard().getName();
        this.discount = discount;
    }

    public String getCardName() {
        return cardName;
    }

    public int getDiscount() {
        return discount;
    }
}
