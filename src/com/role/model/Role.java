package com.role.model;
import com.weapon.model.Weapon;


public interface Role {
    String getName();
    Weapon getRandomWeapon();
    double getHealth();
    void takeDamage( double damage );
    double attack();
    void heal(double hp);
}
