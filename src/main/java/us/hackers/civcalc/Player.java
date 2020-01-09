package us.hackers.civcalc;

import java.util.Set;

public class Player {
    private final String name;
    private final Set<String> cardsOwned;

    public Player(String name, Set<String> cardsOwned) {
        this.name = name;
        this.cardsOwned = cardsOwned;
    }

    public String getName() {
        return name;
    }

    public Set<String> getCardsOwned() {
        return cardsOwned;
    }
}
