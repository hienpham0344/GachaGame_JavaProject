package com.weapon.model;
import com.common.Rarity;

public enum Scepter implements Weapon{
    COMMON("Common Scepter", Rarity.COMMON, 5),
    RARE("Rare Scepter", Rarity.RARE, 10),
    LEGENDARY("Legendary Scepter", Rarity.LEGENDARY, 15);

    private final String name;
    private final Rarity rarity;
    private final int damage;

    Scepter(String name, Rarity rarity, int damage) {
        this.name = name;
        this.rarity = rarity;
        this.damage = damage;
    }

    @Override
    public String getName() { return name; }

    @Override
    public double getDamage() { return damage; }

    @Override
    public Rarity getRarity() { return rarity; }
}
