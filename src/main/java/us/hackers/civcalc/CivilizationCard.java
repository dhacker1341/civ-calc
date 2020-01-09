package us.hackers.civcalc;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class CivilizationCard {
    private final String name;
    private final int cost;
    private final Set<String> groups;
    private final Map<String, Integer> groupCredits;
    private final Map<String, Integer> individualCardCredits;
    private final String attributes;
    private final String calamityEffects;
    private final Set<String> prerequisites;

    public CivilizationCard(String name, int cost, Set<String> groups, Map<String, Integer> groupCredits, Map<String, Integer> individualCardCredits, String attributes, String calamityEffects, Set<String> prerequisites) {
        this.name = name;
        this.cost = cost;
        this.groups = groups;
        this.groupCredits = ImmutableMap.copyOf(groupCredits);
        this.individualCardCredits = ImmutableMap.copyOf(individualCardCredits);
        this.attributes = attributes;
        this.calamityEffects = calamityEffects;
        this.prerequisites = ImmutableSet.copyOf(prerequisites);
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public Set<String> getGroups() {
        return groups;
    }

    public Map<String, Integer> getGroupCredits() {
        return groupCredits;
    }

    public Map<String, Integer> getIndividualCardCredits() {
        return individualCardCredits;
    }

    public String getAttributes() {
        return attributes;
    }

    public String getCalamityEffects() {
        return calamityEffects;
    }

    public Set<String> getPrerequisites() {
        return prerequisites;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CivilizationCard that = (CivilizationCard) o;
        return cost == that.cost &&
                Objects.equals(name, that.name) &&
                Objects.equals(groups, that.groups) &&
                Objects.equals(groupCredits, that.groupCredits) &&
                Objects.equals(individualCardCredits, that.individualCardCredits) &&
                Objects.equals(attributes, that.attributes) &&
                Objects.equals(calamityEffects, that.calamityEffects) &&
                Objects.equals(prerequisites, that.prerequisites);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cost, groups, groupCredits, individualCardCredits, attributes, calamityEffects, prerequisites);
    }
}
