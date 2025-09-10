package com.role.model;
import com.util.WeaponFactory;
import com.weapon.model.Weapon;

public class Warrior implements Role{
    private double health = 90.0;

    @Override
    public String getName() {
        return "Warrior";
    }

    public Weapon getRandomWeapon() {
        return WeaponFactory.getRandomSword();  // chỉ random Sword
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
        return 7.0;
    }

    @Override
    public void useSkill() {
        System.out.println("Health lower than 30% use ultimate skill, DAMAGE BOOSE BY 30%");
    }
}
