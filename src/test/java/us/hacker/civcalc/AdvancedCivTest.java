package us.hacker.civcalc;

import com.google.common.collect.ImmutableList;
import org.junit.Test;
import us.hackers.civcalc.CivilizationCard;
import us.hackers.civcalc.CostCalculator;
import us.hackers.civcalc.advanced.AdvancedCivilizationCard;

import java.util.*;

import static org.junit.Assert.*;
import static us.hackers.civcalc.advanced.AdvancedCivilizationCard.*;

public class AdvancedCivTest {

    private final List<CivilizationCard> FULL_CARD_LIST = ImmutableList.copyOf(AdvancedCivilizationCard.getFullCardList().values());
    private final Map<String, CivilizationCard> NOTHING_OWNED = Collections.emptyMap();
    private final CostCalculator costCalculator = new CostCalculator();

    @Test
    public void testNothingOwnedWith60() {
        assertEquals("", costCalculator.computePossiblePurchases(FULL_CARD_LIST, NOTHING_OWNED, 60).toString());
    }
    @Test
    public void testNothingOwnedWith300() {
        assertEquals("", costCalculator.computePossiblePurchases(FULL_CARD_LIST, NOTHING_OWNED, 300).toString());
    }

    @Test
    public void testCannotBuyAlreadyPurchased() {
        assertFalse(costCalculator.isPurchaseAllowed(MATHEMATICS.getCard(), asCivilizationCardMap(MATHEMATICS)));
    }
    @Test
    public void testCannotBuyWithoutPrereq() {
        assertFalse(costCalculator.isPurchaseAllowed(DEMOCRACY.getCard(), NOTHING_OWNED));
        assertTrue(costCalculator.isPurchaseAllowed(DEMOCRACY.getCard(), asCivilizationCardMap(LAW)));
    }


    @Test
    public void testCanBuyCardNotCurrentlyOwned() {
        assertTrue(costCalculator.isPurchaseAllowed(MATHEMATICS.getCard(), asCivilizationCardMap(ENGINEERING)));
    }

    @Test
    public void testCostWithDiscounts() {
        assertEquals(Optional.of(25), costCalculator.computeCurrentCost(POTTERY.getCard(), asCivilizationCardMap(CLOTH_MAKING, METALWORKING)));
        assertEquals(Optional.of(110), costCalculator.computeCurrentCost(ROADBUILDING.getCard(), asCivilizationCardMap(CLOTH_MAKING, METALWORKING, ENGINEERING)));
        assertEquals(Optional.of(185), costCalculator.computeCurrentCost(MATHEMATICS.getCard(), asCivilizationCardMap(MUSIC, MEDICINE, MYSTICISM)));
    }


    @Test
    public void testCostIsZeroIfDiscountsGreaterThanValue() {
        assertEquals(Optional.of(0), costCalculator.computeCurrentCost(POTTERY.getCard(), asCivilizationCardMap(CLOTH_MAKING, METALWORKING, ENGINEERING, ROADBUILDING, MINING)));
    }

    @Test
    public void testDiscount() {
        assertEquals(15, costCalculator.computeDiscount(THEOLOGY.getCard(), ENLIGHTENMENT.getCard()));
        assertEquals(25, costCalculator.computeDiscount(LAW.getCard(), LITERACY.getCard()));
        assertEquals(0, costCalculator.computeDiscount(METALWORKING.getCard(), MILITARY.getCard()));
        assertEquals(20, costCalculator.computeDiscount(MILITARY.getCard(), METALWORKING.getCard()));
        assertEquals(0, costCalculator.computeDiscount(POTTERY.getCard(), POTTERY.getCard()));
    }
}
