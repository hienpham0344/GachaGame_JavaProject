package com.role.model;

import com.util.WeaponFactory;
import com.weapon.model.Weapon;

public class Mage implements Role {

    private double health = 100.0;


    @Override
    public String getName() {
        return "Mage";
    }

    public Weapon getRandomWeapon() {
        return WeaponFactory.getRandomScepter();  // chỉ random Sword
    }

    @Override
    public double getHealth() {
        return health;
    }

    @Override
    public void takeDamage(double damage) {
        health -= damage;
        if ( health < 0 ) health = 0;
    }

    @Override
    public double attack() {
        return 5.0;
    }

    @Override
    public void useSkill() {
        System.out.println("Health lower than 30% use ultimate skill, HEAL 30 HEALTH");
        health += 30;
    }
}
