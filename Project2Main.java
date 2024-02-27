package Car;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Scanner;

class Vehicle {
    public String make;
    public String model;
    public String color;
    public int odometer;

    public Vehicle(String make, String model, String color) {
        this.make = make;
        this.model = model;
        this.color = color;
        this.odometer = 0;
    }

    Vehicle() {
    }

    public boolean drive(int kilometers) {
        if (kilometers >= 0) {
            odometer += kilometers;
            return true;
        } return false;
    }
    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public int getOdometer() {
        return odometer;
    }
}
class InternalCombustionEngineVehicle extends Vehicle {
    private final double kilometersPerLiter;
    private final double maxLitersInTank;
    private double currentLitersInTank;

    public InternalCombustionEngineVehicle(String make, String model, String color, double kilometersPerLiter, double maxLitersInTank) {
        this.make = make;
        this.model = model;
        this.color = color;
        this.kilometersPerLiter = kilometersPerLiter;
        this.maxLitersInTank = maxLitersInTank;
        this.currentLitersInTank = 0;
    }

    public double getKilometersPerLiter() {
        return kilometersPerLiter;
    }

    public double getMaxLitersInTank() {
        return maxLitersInTank;
    }

    public double getCurrentLitersInTank() {
        return currentLitersInTank;
    }

    public void addGas(double litersOfGas) {
        if (litersOfGas > 0) {
            currentLitersInTank += litersOfGas;
            if (currentLitersInTank > maxLitersInTank) {
                currentLitersInTank = maxLitersInTank;
            }
        }
    }

    public boolean drive(int kilometers) {
        double gasNeeded = kilometers / kilometersPerLiter;
        if (gasNeeded <= currentLitersInTank) {
            currentLitersInTank -= gasNeeded;
            odometer += kilometers;
            return true;
        } else {
            currentLitersInTank = 0;
            return false;
        }
    }
}

class ElectricVehicle extends Vehicle {
    private final double kilometersPerKilowattHour;
    private final double maxKilowattsInBattery;
    private double currentKilowattsInBattery;

    public ElectricVehicle(String make, String model, String color, double kilometersPerKilowattHour, double maxKilowattsInBattery) {
        this.make = make;
        this.model = model;
        this.color = color;
        this.kilometersPerKilowattHour = kilometersPerKilowattHour;
        this.maxKilowattsInBattery = maxKilowattsInBattery;
        this.currentKilowattsInBattery = 0;
    }

    public double getKilometersPerKilowattHour() {
        return kilometersPerKilowattHour;
    }

    public double getMaxKilowattsInBattery() {
        return maxKilowattsInBattery;
    }

    public double getCurrentKilowattsInBattery() {
        return currentKilowattsInBattery;
    }

    public void charge(double kilowattsToCharge) {
        if (kilowattsToCharge > 0) {
            currentKilowattsInBattery += kilowattsToCharge;
            if (currentKilowattsInBattery > maxKilowattsInBattery) {
                currentKilowattsInBattery = maxKilowattsInBattery;
            }
        }
    }

    public boolean drive(int kilometers) {
        double kilowattsNeeded = kilometers / kilometersPerKilowattHour;
        if (kilowattsNeeded <= currentKilowattsInBattery) {
            currentKilowattsInBattery -= kilowattsNeeded;
            odometer += kilometers;
            return true;
        } else {
            currentKilowattsInBattery = 0;
            return false;
        }
    }
}


public class Main {
    @Test
    public void testVehicle() {

        Vehicle vehicle = new Vehicle("Dodge", "Ram", "Red");

        int initialOdometer = vehicle.getOdometer();
        boolean result = vehicle.drive(50);
        int newOdometer = vehicle.getOdometer();

        assertEquals("Dodge", vehicle.getMake());
        assertEquals("Ram", vehicle.getModel());
        assertEquals("Red", vehicle.getColor());
        assertEquals(0, initialOdometer);
        assertEquals(50, newOdometer);
        assertTrue(result);

        boolean negativeResult = vehicle.drive(-50);

        assertEquals(50, newOdometer);
        assertFalse(negativeResult);
    }

    @Test
    public void testInternalCombustionEngineVehicle() {

        InternalCombustionEngineVehicle internalCombustionEngine = new InternalCombustionEngineVehicle("Hyundai", "Sonata", "Silver", 18, 76);


        internalCombustionEngine.addGas(30);
        double initialLitersInTank = internalCombustionEngine.getCurrentLitersInTank();
        boolean result1 = internalCombustionEngine.drive(22);
        double currentLitersInTank1 = internalCombustionEngine.getCurrentLitersInTank();
        boolean result2 = internalCombustionEngine.drive(9);
        double currentLitersInTank2 = internalCombustionEngine.getCurrentLitersInTank();


        assertEquals("Hyundai", internalCombustionEngine.getMake());
        assertEquals("Sonata", internalCombustionEngine.getModel());
        assertEquals("Silver", internalCombustionEngine.getColor());
        assertEquals(0, internalCombustionEngine.getOdometer());
        assertEquals(18, internalCombustionEngine.getKilometersPerLiter());
        assertEquals(76, internalCombustionEngine.getMaxLitersInTank());
        assertEquals(30, initialLitersInTank);
        assertTrue(result1);
        assertEquals(7, currentLitersInTank1);
        assertFalse(result2);
        assertEquals(0, currentLitersInTank2);
    }

    @Test
    public void testElectricVehicle() {

        ElectricVehicle ev = new ElectricVehicle("Chevrolet", "Bolt", "Yellow", 9, 84);

        double initialKilowattsInBattery = ev.getCurrentKilowattsInBattery();
        boolean result1 = ev.drive(138);
        double currentKilowattsInBattery1 = ev.getCurrentKilowattsInBattery();
        boolean result2 = ev.drive(51);
        double currentKilowattsInBattery2 = ev.getCurrentKilowattsInBattery();

        assertEquals("Chevrolet", ev.getMake());
        assertEquals("Bolt", ev.getModel());
        assertEquals("Yellow", ev.getColor());
        assertEquals(0, ev.getOdometer());
        assertEquals(9, ev.getKilometersPerKilowattHour());
        assertEquals(84, ev.getMaxKilowattsInBattery());
        assertEquals(0, initialKilowattsInBattery);
        assertTrue(result1);
        assertEquals(26, currentKilowattsInBattery1);
        assertFalse(result2);
        assertEquals(0, currentKilowattsInBattery2);
    }

    public static void main(String[] args) {

    }
}
