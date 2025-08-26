import java.util.Scanner;

public class Main{
    public static void main( String[]args )
    {
        Scanner userInput = new Scanner(System.in);
        System.out.print("What  item would you like to buy: ");
        String item = userInput.nextLine();
        System.out.print("What is the price: ");
        double price = userInput.nextDouble();
        System.out.print("How many you want to buy: ");
        int amount = userInput.nextInt();

        boolean isMoreThan1 = (amount > 1);

        System.out.println("you have brought " + amount + " " + item + ((isMoreThan1) ? "s" : ""));
        System.out.println("your total is " + price*amount);
    }
}