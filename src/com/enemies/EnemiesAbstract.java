package com.enemies;

public interface EnemiesAbstract {
    String getName();
    double getHealth();
    void getDamage(double damage);
    double attack();
    boolean block();


}
