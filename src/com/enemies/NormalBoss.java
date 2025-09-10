package com.enemies;

public class NormalBoss implements EnemiesAbstract{

    private double health = 150;

    @Override
    public String getName() {
        return "NormalBoss";
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
        return 5.0;
    }

    @Override
    public boolean block() {
        return true;
    }
}
