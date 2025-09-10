package main;

import com.enemies.EnemiesAbstract;
import com.enemies.HardBoss;
import com.enemies.NormalBoss;
import com.player.Player;
import com.role.model.Mage;
import com.role.model.Role;
import com.role.model.Warrior;

import javax.swing.*;
import java.util.Objects;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        // player input for the game - player role, boss, weapon
        String name = JOptionPane.showInputDialog("Enter your name:");

        String[] gameModes = {"Normal", "Hard"};
        int gameMode = JOptionPane.showOptionDialog(
                null,
                "Choose game mode:",
                "Game Mode",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                gameModes,
                gameModes[0]
        );

        String[] roles = {"Mage", "Warrior"};
        int choice = JOptionPane.showOptionDialog(
                null,
                "Pick your role:",
                "Role Selection",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                roles,
                roles[0]
        );

        Role role = (choice == 0) ? new Mage() : new Warrior();
        EnemiesAbstract boss = (gameMode == 0) ? new NormalBoss() : new HardBoss();

        final Player player = new Player(name, role);
        JOptionPane.showMessageDialog(null,
                "🎉 Congratulations, you got " + player.getWeapon() +
                        "\nBoss has: " + boss.getHealth() + " health" +
                        "\nYou have: " + player.getHealth() + " health"
        );

        JOptionPane.showMessageDialog(null, fightingProcess(player, boss));
    }

    public static String fightingProcess(Player player, EnemiesAbstract boss) {
        final double playerMaxHealth = player.getHealth();
        Random rate = new Random();
        boolean use = false;
        double damageBoost = 1.0;

        while (player.getHealth() > 0 && boss.getHealth() > 0) {
            int critRate = rate.nextInt(2);
            double playerArmorPenetration = rate.nextDouble();
            double bossCritRate = rate.nextDouble();
            double bossArmorPenetration = rate.nextDouble();
            int bossChoice = rate.nextInt(2);

            if (!use && player.getHealth() < playerMaxHealth * 0.3) {
                player.useSkill();
                if (Objects.equals(player.getRole(), "Warrior")) {
                    damageBoost += 0.3;
                }
                use = true;
            }

            String[] actions = {"Attack", "Block"};
            int playerChoice = JOptionPane.showOptionDialog(
                    null,
                    "Choose your action:",
                    "Your Turn",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    actions,
                    actions[0]
            );

            StringBuilder roundLog = new StringBuilder("---------------------- ATTACKING ----------------------\n");

            if (bossChoice == 1) { // Boss block
                if (playerChoice == 0) {
                    double damage = player.attack() * playerArmorPenetration * damageBoost;
                    boss.getDamage(damage);
                    roundLog.append(String.format(
                            "Boss blocked! Your attack dealt %.1f (ArmorPen %.2f)\n",
                            damage, playerArmorPenetration
                    ));
                } else {
                    roundLog.append("Both are blocking!\n");
                }
            } else { // Boss attack
                if (playerChoice == 0) { // Player attack
                    double playerDamage;
                    if (critRate == 1) {
                        playerDamage = player.attack() * 1.3 * damageBoost;
                        roundLog.append(String.format("Your attack CRIT! You dealt %.1f\n", playerDamage));
                    } else {
                        playerDamage = player.attack() * damageBoost;
                        roundLog.append(String.format("Your attack hit! You dealt %.1f\n", playerDamage));
                    }
                    boss.getDamage(playerDamage);

                    // Boss phản công
                    double bossDamage;
                    if (bossCritRate > 0.5) { // crit khi > 50%
                        bossDamage = boss.attack() * 1.3;
                        roundLog.append(String.format("Boss CRIT! You took %.1f damage\n", bossDamage));
                    } else {
                        bossDamage = boss.attack();
                        roundLog.append(String.format("Boss hit you! You took %.1f damage\n", bossDamage));
                    }
                    player.getDamage(bossDamage);

                } else { // Player block
                    double damage = boss.attack() * bossArmorPenetration;
                    player.getDamage(damage);
                    roundLog.append(String.format(
                            "You blocked! Took %.1f damage (Boss ArmorPen %.2f)\n",
                            damage, bossArmorPenetration
                    ));
                }
            }

            roundLog.append("\n---------------------- STATUS ----------------------\n");
            roundLog.append(String.format("Player HP: %.1f\n", (player.getHealth() < 0) ? 0 : player.getHealth()));
            roundLog.append(String.format("Boss HP: %.1f\n", (boss.getHealth() < 0) ? 0 : boss.getHealth()));

            JOptionPane.showMessageDialog(null, roundLog.toString());
        }

        return (player.getHealth() <= 0) ? "💀 You lose!" : "🏆 You win!";
    }
}
