package Chapter2;

public class Lab1 {
    public static void main(String[] args) {

        double numberWithADecimal = 3.99;
        double totalCostOfWater = Math.round(numberWithADecimal * 5);

        System.out.println("""
                Write a program that asks the user:

                How much do you spend on water bottles, per month, knowing the purchase frequency is 5 units in a month?
                Supply the value for the cost of the pack of water bottles.
                """);


        System.out.println("The cost of one pack of water bottles is " + numberWithADecimal);

        System.out.println("When purchasing 5 packs of water at " + numberWithADecimal);

        System.out.println("The answer would be about: " + totalCostOfWater);
    }
}
