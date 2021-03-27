package com.TekkenInfo.Domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

public class Char {
    @NotBlank(message="Пожалуйста, введите имя персонажа")
    @Length(max=30, message = "Слишком длинное имя, максимальное число символов 30")
    private String name;
    @NotBlank(message="Пожалуйста, введите боевой стиль персонажа")
    @Length(max=50, message = "Слишком длинное название боевого стиля, максимальное число символов 50")
    private String fightingStyle;
    private Tier tierLvl;
    public Char(){}

    public Char(String name, String fightingStyle, String tierLvl) {
        this.name = name;
        this.fightingStyle = fightingStyle;
        this.tierLvl = Tier.valueOf(tierLvl);
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