package com.tesda8.region8.util.enums;

public enum AgeGroup {
    GROUP_1("15-25", 15, 25),
    GROUP_2("26-35", 26, 35),
    GROUP_3("36-45", 36, 45),
    GROUP_4("46-55", 46, 55),
    GROUP_5("56-65", 56, 65),
    GROUP_6("66 and Above", 66, 100);

    public final String label;
    public final int min;
    public final int max;

    private AgeGroup(String label, int min, int max) {
        this.label = label;
        this.min = min;
        this.max = max;
    }
}
