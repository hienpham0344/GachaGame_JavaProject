package com.util;
import com.common.Rarity;
import com.weapon.model.*;
import java.util.Random;


public class WeaponFactory {
    private static final Random random = new Random();

    private static Rarity getRandomRarity() {
        double roll = random.nextDouble();
        double cumulative = 0.0;
        for (Rarity rarity : Rarity.values()) {
            cumulative += rarity.getDropRate();
            if (roll <= cumulative) return rarity;
        }
        return Rarity.COMMON;
    }

    public static Weapon getRandomSword() {
        Rarity rarity = getRandomRarity();
        return switch (rarity) {
            case COMMON -> Sword.COMMON;
            case RARE -> Sword.RARE;
            case LEGENDARY -> Sword.LEGENDARY;
        };
    }

    public static Weapon getRandomScepter() {
        Rarity rarity = getRandomRarity();
        return switch (rarity) {
            case COMMON -> Scepter.COMMON;
            case RARE -> Scepter.RARE;
            case LEGENDARY -> Scepter.LEGENDARY;
        };
    }
}
