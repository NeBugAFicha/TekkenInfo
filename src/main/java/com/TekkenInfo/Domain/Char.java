package com.TekkenInfo.Domain;

public class Char {
    private String name;
    private String fightingStyle;
    private Tier tierLvl;
    public Char(){}

    public Char(String name, String fightingStyle, Tier tierLvl) {
        this.name = name;
        this.fightingStyle = fightingStyle;
        this.tierLvl = tierLvl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFightingStyle() {
        return fightingStyle;
    }

    public void setFightingStyle(String fightingStyle) {
        this.fightingStyle = fightingStyle;
    }

    public Tier getTierLvl() {
        return tierLvl;
    }

    public void setTierLvl(Tier tierLvl) {
        this.tierLvl = tierLvl;
    }
}