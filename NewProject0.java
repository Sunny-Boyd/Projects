import java.util.Scanner;

public class NewProject0 {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);


        System.out.println("What is the length of your fence area?");
        int length = Integer.parseInt(keyboard.nextLine());


        System.out.println("What is the width of your fence area?");
        int width = Integer.parseInt(keyboard.nextLine());


        double fencePerimeter = length*2 + width*2;
        System.out.println("Total fenced perimeter is " +fencePerimeter);
        System.out.println();


        System.out.println("How far apart should posts be?");
        int postsApart = Integer.parseInt(keyboard.nextLine());


        if (postsApart != 0 && fencePerimeter % postsApart == 0) {
            //Moves on
        } else {
            System.out.println(fencePerimeter + " is not evenly divisible, please try again. " + postsApart);
            System.exit(1);
        }


        System.out.println();
        double numberOfPosts = fencePerimeter / postsApart;
        System.out.println("Amount of posts needed: " + numberOfPosts);
        System.out.println();


        System.out.println("Length of board needed?");
        int boardLength = Integer.parseInt(keyboard.nextLine());
        System.out.println();


        if (boardLength < postsApart) {
            System.out.println("Board length is less than the distance between posts, please try again.");
            System.exit(1);
        } else {//Moves on
        }

        System.out.println("Number of boards needed to build fence with a single board across");
        int boardAcrossLength = length / boardLength;
        System.out.println(+numberOfPosts);
        System.out.println();


        System.out.println("How many boards across each post?");
        int boardsBetween = Integer.parseInt(keyboard.nextLine());
        System.out.println();


        System.out.println("Total number of boards required?");
        double totalBoardsNeeded = (numberOfPosts * boardsBetween);
        System.out.println(+totalBoardsNeeded);
        System.out.println();


        System.out.println("Price of each post?");
        double postPrice = Double.parseDouble(keyboard.nextLine());
        System.out.println("Price of each board?");
        double boardPrice = Double.parseDouble(keyboard.nextLine());
        System.out.println();


        System.out.println("Total cost for posts:");
        double totalPostPrice = postPrice * numberOfPosts;
        System.out.println(totalPostPrice);
        System.out.println();

        System.out.println("Total cost for boards:");
        double totalBoardPrice = boardPrice * totalBoardsNeeded;
        System.out.println(totalBoardPrice);
        System.out.println();


        System.out.println("Project grand total: ");
        double grandTotal = totalBoardPrice + totalPostPrice;
        System.out.println(+ grandTotal);



    }
}