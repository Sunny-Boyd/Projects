import java.util.Scanner;

public class lab2 {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("What is your hourly wage?");
        double wage = Double.parseDouble(keyboard.nextLine());

        System.out.println("How many hours have you worked this week?");
        int hours = Integer.parseInt(keyboard.nextLine());
        System.out.println();


        double lessthan40;
        double overtime = 0.0;


        if (hours <=40) {
            lessthan40 = wage * hours;
        }
        else {
            lessthan40 = wage * 40;
            overtime = (hours - 40) * (wage * 1.5);
        }

        double payForWeek = lessthan40 + overtime;

        System.out.println("Your gross pay is: ");
        System.out.println(payForWeek);
        System.out.println();

        double taxes = payForWeek * .10;


        System.out.println("Taxes owed is 10% of your gross pay, you have to pay this much in taxes: ");
        System.out.println(taxes);
        System.out.println();

        double netPay = payForWeek - taxes;

        System.out.println("This is your net pay for the week: ");
        System.out.println(netPay);



    }

}