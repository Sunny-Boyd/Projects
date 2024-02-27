import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;


class PersonHeartRate {
    private String firstName;
    private String lastName;
    private DateOfBirth dateOfBirth;

    public PersonHeartRate(String firstName, String lastName, DateOfBirth dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public DateOfBirth getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(DateOfBirth dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int calculateAge() {
        Calendar currentDate = Calendar.getInstance();
        int currentYear = currentDate.get(Calendar.YEAR);

        return currentYear - dateOfBirth.getYear();
    }

    public int calculateMaxHeartRate() {
        return 220 - calculateAge();
    }

    public String calculateTargetHeartRateRange() {
        int maxHeartRate = calculateMaxHeartRate();
        int lowerRange = (int) (maxHeartRate * 0.5);
        int upperRange = (int) (maxHeartRate * 0.85);

        return lowerRange + " - " + upperRange;
    }

    public void printData() {
        System.out.println(lastName + ", " + firstName);
        System.out.println("Max heart rate: " + calculateMaxHeartRate());
        System.out.println("Target heart rate: " + calculateTargetHeartRateRange());
    }
}