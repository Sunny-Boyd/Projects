package rockpaperscissors;

import java.util.Scanner;

public class rps {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);


        String[] choices = {"Rock", "Paper", "Scissors", "Lizard", "Spock"};


        while (true) {

            System.out.println("Rock, Paper, Scissors, Lizard, Spock!");


            System.out.println("Choose one to start playing: ");
            String player = keyboard.nextLine();


            if (!right(player)) {
                System.out.println("Invalid choice. Please try again.");
                System.out.println();
            } else {

                int randomNumber = (int) (Math.random() * 5);
                String computer = choices[randomNumber];
                System.out.println("Computer's choice is: " + computer);
                System.out.println();


                String results = winLoseTie(player, computer);
                System.out.println(results);

                System.out.println();
                System.out.println("Play again? Y/N: ");
                String playAgainYN = keyboard.nextLine();

                if (!playAgainYN.equalsIgnoreCase("y")) {
                    System.out.println("Till next time.");
                    break;
                }

            }
        }
    }


    public static String winLoseTie(String playerChoice, String computerChoice) {
        if (playerChoice.equalsIgnoreCase(computerChoice)) {
            return "It's a tie.";
        } else if ((playerChoice.equalsIgnoreCase("Rock") && (computerChoice.equalsIgnoreCase("Scissors") || computerChoice.equalsIgnoreCase("Lizard"))) ||
                (playerChoice.equalsIgnoreCase("Paper") && (computerChoice.equalsIgnoreCase("Rock") || computerChoice.equalsIgnoreCase("Spock"))) ||
                (playerChoice.equalsIgnoreCase("Scissors") && (computerChoice.equalsIgnoreCase("Paper") || computerChoice.equalsIgnoreCase("Lizard"))) ||
                (playerChoice.equalsIgnoreCase("Lizard") && (computerChoice.equalsIgnoreCase("Spock") || computerChoice.equalsIgnoreCase("Paper"))) ||
                (playerChoice.equalsIgnoreCase("Spock") && (computerChoice.equalsIgnoreCase("Scissors") || computerChoice.equalsIgnoreCase("Rock")))) {
            return "Winner Winner Chicken Dinner!";
        } else {
            return "You Lose, Computer Wins!";
        }
    }


    public static boolean right(String correct) {
        return correct.equalsIgnoreCase("Rock") || correct.equalsIgnoreCase("Paper") ||
                correct.equalsIgnoreCase("Scissors") || correct.equalsIgnoreCase("Lizard") ||
                correct.equalsIgnoreCase("Spock");
    }
}

