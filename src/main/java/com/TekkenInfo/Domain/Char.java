package com.TekkenInfo.Domain;

import org.hibernate.validator.constraints.Length;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotBlank;

public class Char {

    @NotBlank(message="Пожалуйста, введите имя персонажа")
    @Length(max=30, message = "Слишком длинное имя, максимальное число символов 30")
    private String name;
    @NotBlank(message="Пожалуйста, введите боевой стиль персонажа")
    @Length(max=30, message = "Слишком длинное название боевого стиля, максимальное число символов 30")
    private String fightingStyle;
    private Tier tierLvl;
    private String image;
    private String charMakerName;
    public Char(){}

    public Char(String name, String fightingStyle, String tierLvl, String image) {
        this.name = name;
        this.fightingStyle = fightingStyle;
        this.tierLvl = Tier.valueOf(tierLvl);
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCharMakerName() {
        return charMakerName;
    }

    public void setCharMakerName(String charMakerName) {
        this.charMakerName = charMakerName;
    }
}
