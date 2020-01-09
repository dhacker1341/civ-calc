package us.hackers.civcalc;

import java.util.Set;

public class Credit {
    private final String type;
    private  final int amount;
    private  final Set<String> excludeTypes;

    public Credit(String type, int amount, Set<String> excludeTypes) {
        this.type = type;
        this.amount = amount;
        this.excludeTypes = excludeTypes;
    }

    public String getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    public Set<String> getExcludeTypes() {
        return excludeTypes;
    }
}
