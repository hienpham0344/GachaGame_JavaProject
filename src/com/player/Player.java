package com.player;
import com.role.model.Role;
import com.util.WeaponFactory;
import com.weapon.model.Weapon;

public class Player {
    private final String name;
    private final Role role;
    private final Weapon weapon;

    public Player(String name, Role role)
    {
        this.name = name;
        this.role = role;
        weapon = role.getRandomWeapon();
    }


    public String getName() {
        return name;
    }

    public String getRole() {
        return role.getName();
    }

    public String getWeapon() {
        return weapon.getName();
    }

    public double getHealth()
    {
        return role.getHealth();
    }

    public void useSkill()
    {
        role.useSkill();
    }

    public void getDamage( double damage)
    {
        role.takeDamage(damage);
    }

    public double attack()
    {
        return (role.attack() + weapon.getDamage())/2;
    }

    public boolean block(){
        return true;
    }
}
