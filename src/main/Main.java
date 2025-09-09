package main;
import com.player.Player;
import com.role.model.Mage;
import com.role.model.Role;
import com.role.model.Warrior;

import java.util.Scanner;

public class Main
{

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("enter your name: ");
        String name = input.nextLine();
        System.out.print("pick your role ( 1-mage,2-warrior ): ");
        int choice = input.nextInt();
        Role role = ( choice == 1 ) ? new Mage() : new Warrior();

        Player player = new Player(name,role);

        System.out.println(player.getName());
        System.out.println(player.getRole());
        System.out.println(player.getWeapon());


    }
}

/* note
- tối ưu cơ chế gây damage
- cơ chế thông báo người chơi đã chết
- các class boss và thông tin của boss
- các cơ chế chiêu đặc biệt
- các exception cần lưu ý
- làm clean code
 */