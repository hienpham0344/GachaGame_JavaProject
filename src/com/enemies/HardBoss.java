package com.enemies;

public class HardBoss implements EnemiesAbstract{

    private double health = 200;

    @Override
    public String getName() {
        return "HardBoss";
    }

    @Override
    public double getHealth() {
        return health;
    }

    @Override
    public void getDamage(double damage) {
        health -= damage;

    }

    @Override
    public double attack() {
        return 8.0;
    }

    @Override
    public boolean block() {
        return true;
    }
}
