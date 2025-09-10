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

public class
Main {

    public static void main(String[] args) {
        String name = GameUI.getInput("Enter your name:");

        // pick mode
        String[] gameModes = {"Normal", "Hard"};
        int gameMode = GameUI.chooseOption("Choose game mode:", "Game Mode", gameModes);

        // pick role
        String[] roles = {"Mage", "Warrior"};
        int choice = GameUI.chooseOption("Pick your role:", "Role Selection", roles);

        Role role = (choice == 0) ? new Mage() : new Warrior();
        EnemiesAbstract boss = (gameMode == 0) ? new NormalBoss() : new HardBoss();

        final Player player = new Player(name, role);
        GameUI.showMessage("🎉 Congratulations, you got " + player.getWeapon() +
                "\nBoss has: " + boss.getHealth() + " health" +
                "\nYou have: " + player.getHealth() + " health");

        GameUI.showMessage(fightingProcess(player, boss));
    }

    public static String fightingProcess(Player player, EnemiesAbstract boss) {
        final double playerMaxHealth = player.getHealth();
        Random rate = new Random();
        boolean useSkill = false;
        double damageBoost = 1.0;

        while (player.getHealth() > 0 && boss.getHealth() > 0) {
            int critRate = rate.nextInt(2);
            double playerArmorPen = rate.nextDouble();
            double bossCritRate = rate.nextDouble();
            double bossArmorPen = rate.nextDouble();
            int bossChoice = rate.nextInt(2);

            // Check ultimate skill
            if (!useSkill && player.getHealth() < playerMaxHealth * 0.3) {
                useSkill = true;
                if (Objects.equals(player.getRole(), "Warrior")) {
                    GameUI.showMessage("⚡ Health lower than 30%! Warrior uses ultimate skill: DAMAGE +30%!");
                    damageBoost += 0.3;
                } else {
                    player.heal(30); // thêm hàm heal trong Player
                    GameUI.showMessage("✨ Health lower than 30%! Mage uses ultimate skill: HEAL +30 HP!");
                }
            }

            // Player action
            String[] actions = {"Attack", "Block"};
            int playerChoice = GameUI.chooseOption("Choose your action:", "Your Turn", actions);

            // Battle log
            BattleLog log = new BattleLog();
            log.add("---------------------- ATTACKING ----------------------");

            if (bossChoice == 1) { // Boss block
                if (playerChoice == 0) {
                    double damage = player.attack() * playerArmorPen * damageBoost;
                    boss.getDamage(damage);
                    log.add(String.format("Boss blocked! Your attack dealt %.1f (ArmorPen %.2f)", damage, playerArmorPen));
                } else {
                    log.add("Both are blocking!");
                }
            } else { // Boss attack
                if (playerChoice == 0) { // Player attack
                    double playerDamage = calcDamage(player.attack(), critRate == 1, damageBoost);
                    boss.getDamage(playerDamage);
                    log.add((critRate == 1) ? "Your attack CRIT! You dealt " + playerDamage
                            : "Your attack hit! You dealt " + playerDamage);

                    double bossDamage = calcDamage(boss.attack(), bossCritRate > 0.5, 1.0);
                    player.takeDamage(bossDamage);
                    log.add((bossCritRate > 0.5) ? "Boss CRIT! You took " + bossDamage
                            : "Boss hit you! You took " + bossDamage);

                } else { // Player block
                    double damage = boss.attack() * bossArmorPen;
                    player.takeDamage(damage);
                    log.add(String.format("You blocked! Took %.1f damage (Boss ArmorPen %.2f)", damage, bossArmorPen));
                }
            }

            log.add("\n---------------------- STATUS ----------------------");
            log.add(String.format("Player HP: %.1f", Math.max(0, player.getHealth())));
            log.add(String.format("Boss HP: %.1f", Math.max(0, boss.getHealth())));
            log.show();
        }

        return (player.getHealth() <= 0) ? "💀 You lose!" : "🏆 You win!";
    }

    private static double calcDamage(double baseDamage, boolean crit, double multiplier) {
        return crit ? baseDamage * 1.3 * multiplier : baseDamage * multiplier;
    }
}

/** Class BattleLog để gom log */
class BattleLog {
    private final StringBuilder sb = new StringBuilder();

    public void add(String msg) {
        sb.append(msg).append("\n");
    }

    public void show() {
        JOptionPane.showMessageDialog(null, sb.toString());
    }
}

/** Class GameUI để gom toàn bộ UI */
class GameUI {
    public static void showMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    public static String getInput(String msg) {
        return JOptionPane.showInputDialog(msg);
    }

    public static int chooseOption(String msg, String title, String[] options) {
        return JOptionPane.showOptionDialog(
                null, msg, title,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]
        );
    }
}
