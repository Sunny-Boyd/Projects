import java.util.ArrayList;
import java.util.Scanner;


public class HeartRateDemo {
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    ArrayList<PersonHeartRate> heartRateList = new ArrayList<>();

    while (true) {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter birth month: ");
        int birthMonth = scanner.nextInt();

        System.out.print("Enter birth day: ");
        int birthDay = scanner.nextInt();

        System.out.print("Enter birth year: ");
        int birthYear = scanner.nextInt();

        DateOfBirth dateOfBirth = new DateOfBirth(birthMonth, birthDay, birthYear);

        PersonHeartRate personHeartRate = new PersonHeartRate(firstName, lastName, dateOfBirth);
        heartRateList.add(personHeartRate);

        System.out.print("Do you want to add another person's information? (yes/no): ");
        scanner.nextLine();
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("no")) {
            break;
        }
    }

    for (PersonHeartRate person : heartRateList) {
        person.printData();
        System.out.println();
    }

    scanner.close();
    }
}
