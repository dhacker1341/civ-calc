package us.hackers.civcalc.advanced;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import us.hackers.civcalc.CivilizationCard;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public enum AdvancedCivilizationCard {
    LAW("Law", 170, AdvancedCivGroup.CIVICS, 0, "", ""),
    DEMOCRACY("Democracy", 200, AdvancedCivGroup.CIVICS, 0, "Law", "", ""),
    MONOTHEISM("Monotheism", 220, AdvancedCivGroup.RELIGION, 0, "Enlightenment", "", ""),
    THEOLOGY("Theology", 250, AdvancedCivGroup.RELIGION, 0,"Enlightenment", "", ""),
    ENLIGHTENMENT("Enlightenment", 150, AdvancedCivGroup.RELIGION , 0, "", "", new CardDiscount(AdvancedCivilizationCard.MONOTHEISM, 15), new CardDiscount(AdvancedCivilizationCard.THEOLOGY, 15)),
    PHILOSOPHY("Philosphy", 240, AdvancedCivGroup.CIVICS, 0, "Law", "", ""),
    MATHEMATICS("Mathematics", 230, AdvancedCivGroup.SCIENCES,20, AdvancedCivGroup.ARTS,5, "", "", new CardDiscount(AdvancedCivilizationCard.PHILOSOPHY, 25), new CardDiscount(AdvancedCivilizationCard.THEOLOGY, 25), new CardDiscount(AdvancedCivilizationCard.ENLIGHTENMENT, 10)),
    MILITARY("Military", 180, AdvancedCivGroup.CIVICS, 0, "", ""),
    LITERACY("Literacy", 110, AdvancedCivGroup.ARTS, 5, AdvancedCivGroup.CIVICS, 0, "", "", new CardDiscount(AdvancedCivilizationCard.ENLIGHTENMENT, 10), new CardDiscount(AdvancedCivilizationCard.LAW, 25), new CardDiscount(AdvancedCivilizationCard.DEMOCRACY, 25), new CardDiscount(AdvancedCivilizationCard.PHILOSOPHY, 25)),

    POTTERY("Pottery", 45, AdvancedCivGroup.CRAFTS, 10, "", "", new CardDiscount(AdvancedCivilizationCard.DEMOCRACY, 10), new CardDiscount(AdvancedCivilizationCard.MONOTHEISM, 10)),
    CLOTH_MAKING("Cloth Making", 45, AdvancedCivGroup.CRAFTS,10, "", "", new CardDiscount(AdvancedCivilizationCard.DEMOCRACY, 10), new CardDiscount(AdvancedCivilizationCard.MONOTHEISM, 10)),
    METALWORKING("Metalworking", 80, AdvancedCivGroup.CRAFTS, 10, "", "", new CardDiscount(AdvancedCivilizationCard.DEMOCRACY, 10), new CardDiscount(AdvancedCivilizationCard.MONOTHEISM, 10), new CardDiscount(AdvancedCivilizationCard.MILITARY, 20)),
    AGRICULTURE("Agriculture", 110, AdvancedCivGroup.CRAFTS, 10, "", "", new CardDiscount(AdvancedCivilizationCard.DEMOCRACY, 10), new CardDiscount(AdvancedCivilizationCard.MONOTHEISM, 10)),
    ROADBUILDING("Roadbuilding", 140, AdvancedCivGroup.CRAFTS, 10, "Engineering", "", "", new CardDiscount(AdvancedCivilizationCard.DEMOCRACY, 10), new CardDiscount(AdvancedCivilizationCard.MONOTHEISM, 10)),
    MINING("Mining", 180, AdvancedCivGroup.CRAFTS,10,"Engineering", "", "", new CardDiscount(AdvancedCivilizationCard.DEMOCRACY, 10), new CardDiscount(AdvancedCivilizationCard.MONOTHEISM, 10)),
    ENGINEERING("Engineering", 140, AdvancedCivGroup.CRAFTS, 10, AdvancedCivGroup.SCIENCES, 20, "", "", new CardDiscount(AdvancedCivilizationCard.DEMOCRACY, 10), new CardDiscount(AdvancedCivilizationCard.MONOTHEISM, 10) , new CardDiscount(AdvancedCivilizationCard.PHILOSOPHY, 20), new CardDiscount(AdvancedCivilizationCard.THEOLOGY, 20)),
    ASTRONOMY("Astronomy", 80, AdvancedCivGroup.SCIENCES, 20, "", "", new CardDiscount(AdvancedCivilizationCard.PHILOSOPHY, 20), new CardDiscount(AdvancedCivilizationCard.THEOLOGY, 20)),
    COINAGE("Coinage", 110, AdvancedCivGroup.SCIENCES, 20, "", "", new CardDiscount(AdvancedCivilizationCard.PHILOSOPHY, 20), new CardDiscount(AdvancedCivilizationCard.THEOLOGY, 20)),
    MEDICINE("Medicine", 140, AdvancedCivGroup.SCIENCES, 20, "", "", new CardDiscount(AdvancedCivilizationCard.PHILOSOPHY, 20), new CardDiscount(AdvancedCivilizationCard.THEOLOGY, 20)),
    DRAMA_AND_POETRY("Drama & Poetry", 60, AdvancedCivGroup.ARTS, 5, "", "", new CardDiscount(AdvancedCivilizationCard.DEMOCRACY, 10), new CardDiscount(AdvancedCivilizationCard.ENLIGHTENMENT, 10), new CardDiscount(AdvancedCivilizationCard.LITERACY, 20)),
    MUSIC("Music", 60, AdvancedCivGroup.ARTS, 5, "", "", new CardDiscount(AdvancedCivilizationCard.DEMOCRACY, 10), new CardDiscount(AdvancedCivilizationCard.ENLIGHTENMENT, 10), new CardDiscount(AdvancedCivilizationCard.MATHEMATICS, 20), new CardDiscount(AdvancedCivilizationCard.PHILOSOPHY, 20)),
    ARCHITECTURE("Architecture", 120, AdvancedCivGroup.ARTS, 5, "", "", new CardDiscount(AdvancedCivilizationCard.DEMOCRACY, 10), new CardDiscount(AdvancedCivilizationCard.ENLIGHTENMENT, 10), new CardDiscount(AdvancedCivilizationCard.LAW, 15)),
    MYSTICISM("Mysticism", 50, AdvancedCivGroup.ARTS, 5, AdvancedCivGroup.RELIGION, 15, "", ""),
    DEISM("Deism", 80, AdvancedCivGroup.RELIGION, 0, "", "", new CardDiscount(AdvancedCivilizationCard.ENLIGHTENMENT, 15), new CardDiscount(AdvancedCivilizationCard.MONOTHEISM, 15), new CardDiscount(AdvancedCivilizationCard.THEOLOGY, 15)),
    ;

    private final CivilizationCard card;

    AdvancedCivilizationCard(String name, int value, AdvancedCivGroup group, int groupDiscount, String attributes, String calamityEffects, CardDiscount... cardDiscounts) {
        this(
                new CivilizationCard(name,
                        value,
                        ImmutableSet.of(group.name()),
                        ImmutableMap.of(group.name(), groupDiscount),
                        getCardCredits(cardDiscounts), null, null,
                        ImmutableSet.of()));
    }

    AdvancedCivilizationCard(String name, int value, AdvancedCivGroup group, int groupDiscount, String prereq, String attributes, String calamityEffects, CardDiscount... cardDiscounts) {
        this(
                new CivilizationCard(name,
                        value,
                        ImmutableSet.of(group.name()),
                        ImmutableMap.of(group.name(), groupDiscount),
                        getCardCredits(cardDiscounts), null, null,
                        ImmutableSet.of(prereq)));
    }
    AdvancedCivilizationCard(String name, int value, AdvancedCivGroup group1, int group1Discount, AdvancedCivGroup group2, int group2Discount, String attributes, String calamityEffects, CardDiscount... cardDiscounts) {
        this(
                new CivilizationCard(name,
                        value,
                        ImmutableSet.of(group1.name(), group2.name()),
                        ImmutableMap.of(group1.name(), group1Discount, group2.name(), group2Discount),
                        getCardCredits(cardDiscounts), null, null,
                        ImmutableSet.of()));
    }

    AdvancedCivilizationCard(CivilizationCard card) {
        this.card = card;
    }

    private static Map<String, Integer> getCardCredits(CardDiscount[] cardDiscounts) {
        ImmutableMap.Builder<String, Integer> builder = ImmutableMap.builder();
        for (CardDiscount d : cardDiscounts) {
            builder.put(d.getCardName(), d.getDiscount());
        }
        return builder.build();
    }

    public CivilizationCard getCard() {
        return card;
    }

    public static Map<String, CivilizationCard> getFullCardList() {
        return asCivilizationCardMap(AdvancedCivilizationCard.values());
    }

    public static Map<String, CivilizationCard> asCivilizationCardMap(AdvancedCivilizationCard... cards) {
        return Arrays.stream(cards).map(AdvancedCivilizationCard::getCard).collect(Collectors.toMap(CivilizationCard::getName, c -> c));
    }

}
