package us.hackers.civcalc.advanced;

public enum AdvancedCivGroup {
    CRAFTS("orange"),
    SCIENCES("green"),
    ARTS("blue"),
    CIVICS("red"),
    RELIGION("yellow"),
    ;
    private final String color;

    AdvancedCivGroup(String color)  {
        this.color = color;
    }
}
