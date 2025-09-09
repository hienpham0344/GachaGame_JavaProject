package com.weapon.model;
import com.common.Rarity;

public enum Sword implements Weapon{
    COMMON("Common Sword", Rarity.COMMON, 5),
    RARE("Rare Sword", Rarity.RARE, 10),
    LEGENDARY("Legendary Sword", Rarity.LEGENDARY, 15);

    private final String name;
    private final Rarity rarity;
    private final int damage;

    Sword(String name, Rarity rarity, int damage) {
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
