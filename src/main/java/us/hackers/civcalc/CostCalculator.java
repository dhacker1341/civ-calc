package us.hackers.civcalc;

import com.google.common.collect.Sets;

import java.util.*;

public class CostCalculator {

    public List<PossiblePurchase> computePossiblePurchases(
            List<CivilizationCard> fullCardList,
            Map<String, CivilizationCard> cardsCurrentlyOwned,
            int maximumPurchaseCost) {
        List<PossiblePurchase> possiblePurchases = new ArrayList<>();
        Map<String, CardWithCurrentCost> affordableCards = computeCurrentCostsOfAffordableCards(fullCardList, cardsCurrentlyOwned, maximumPurchaseCost);
        Set<Set<String>> possiblePurchaseSets = Sets.powerSet(affordableCards.keySet());
        for (Set<String> possiblePurchase : possiblePurchaseSets) {
            if (possiblePurchase.isEmpty()) continue;
            int costOfPurchase = 0;
            int valueOfPurchase = 0;
            Set<CardWithCurrentCost> cards = new HashSet<>();
            for (String cardNameForPurchase : possiblePurchase) {
                CardWithCurrentCost cardWithCurrentCost = affordableCards.get(cardNameForPurchase);
                cards.add(cardWithCurrentCost);
                costOfPurchase += cardWithCurrentCost.getCost();
                valueOfPurchase += cardWithCurrentCost.getCardToPurchase().getCost();
            }
            if (costOfPurchase <= maximumPurchaseCost) {
                possiblePurchases.add(new PossiblePurchase(cards, costOfPurchase, valueOfPurchase));
            }
        }
        return possiblePurchases;
    }

    public Map<String, CardWithCurrentCost> computeCurrentCostsOfAffordableCards(List<CivilizationCard> fullCardList,
                                                    Map<String, CivilizationCard> cardsCurrentlyOwned,
                                                    int maxCost) {
        Map<String, CardWithCurrentCost> currentCosts = new HashMap<>();
        for (CivilizationCard cardToPurchase : fullCardList) {
            computeCurrentCost(cardToPurchase, cardsCurrentlyOwned)
                    .ifPresent(cost -> {
                        if (cost <= maxCost) {
                            currentCosts.put(cardToPurchase.getName(), new CardWithCurrentCost(cardToPurchase, cost));
                        }
                    });
        }
        return currentCosts;
    }

    public Optional<Integer> computeCurrentCost(CivilizationCard cardToPurchase,
                                                Map<String, CivilizationCard> cardsCurrentlyOwned) {
        if (!isPurchaseAllowed(cardToPurchase, cardsCurrentlyOwned)) {
            return Optional.empty();
        }
        int discount = 0;
        for (CivilizationCard ownedCard : cardsCurrentlyOwned.values()) {
            discount += computeDiscount(cardToPurchase, ownedCard);
        }
            return Optional.of(Math.max(0, cardToPurchase.getCost() - discount));
    }

    public int computeDiscount(CivilizationCard cardToPurchase, CivilizationCard ownedCard) {
        if (cardToPurchase.equals(ownedCard)) {
            return 0;
        }
        Map<String, Integer> individualCardCredits = ownedCard.getIndividualCardCredits();
        if (individualCardCredits.containsKey(cardToPurchase.getName())) {
            return individualCardCredits.get(cardToPurchase.getName());
        }


        Map<String, Integer> groupCredits = ownedCard.getGroupCredits();
        for (String group : cardToPurchase.getGroups()) {
            if (groupCredits.containsKey(group)) {
                return groupCredits.get(group);
            }
        }

        return 0;
    }

    public boolean isPurchaseAllowed(CivilizationCard card, Map<String, CivilizationCard> cardsCurrentlyOwned) {
        return cardsCurrentlyOwned.keySet().containsAll(card.getPrerequisites()) && !cardsCurrentlyOwned.containsKey(card.getName());
    }

}
