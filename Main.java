package Project3;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Ticket {
    private int ticketNumber;
    private String purchaserName;
    private int randomCode;

    public Ticket(int ticketNumber) {
        this.ticketNumber = ticketNumber;
        this.purchaserName = "";
        this.randomCode = 0;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public String getPurchaserName() {
        return purchaserName;
    }

    public int getRandomCode() {
        return randomCode;
    }

    public void sellTicket(String purchaserName) {
        if (this.purchaserName.equals("")) {
            this.purchaserName = purchaserName;
            this.randomCode = new Random().nextInt(900000) + 100000;
        }
    }

    public boolean isAvailable() {
        return purchaserName.equals("") && randomCode == 0;
    }

    @Override
    public String toString() {
        return "Ticket #" + ticketNumber +
                ", Purchaser Name: " + purchaserName +
                ", Confirmation Code: " + randomCode;
    }
}

public class Main {
    public static void main(String[] args) {
        ArrayList<Ticket> tickets = loadTicketsFromFile("ticket_data.txt");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Options are:\n1. Manage Shows\n2. Sell Tickets\n3. Display Report\n4. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                manageShows(tickets);
                saveTicketsToFile(tickets, "ticket_data.txt");
            } else if (choice == 2) {
                sellTickets(tickets);
                saveTicketsToFile(tickets, "ticket_data.txt");
            } else if (choice == 3) {
                displayShowReport(tickets);
            } else if (choice == 4) {
                System.out.println("Exiting.");
                System.exit(0);
            } else {
                System.out.println("Invalid choice, please try again.");
            }
        }
    }

    public static void manageShows(ArrayList<Ticket> tickets) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the show: ");
        String showName = scanner.nextLine();

        System.out.print("Enter the number of tickets to create: ");
        int numTickets = scanner.nextInt();

        int ticketNumber = tickets.size() + 1;

        for (int i = 0; i < numTickets; i++) {
            Ticket ticket = new Ticket(ticketNumber);
            tickets.add(ticket);
            ticketNumber++;
        }

        System.out.println("Tickets for show '" + showName + "' created.");
    }

    public static void sellTickets(ArrayList<Ticket> tickets) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the show: ");
        String showName = scanner.nextLine();

        System.out.print("Enter the desired ticket number: ");
        int desiredTicketNumber = scanner.nextInt();

        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter the purchaser's name: ");
        String purchaserName = scanner.nextLine();

        boolean ticketFound = false;

        for (Ticket ticket : tickets) {
            if (ticket.isAvailable() && ticket.getTicketNumber() == desiredTicketNumber) {
                ticket.sellTicket(purchaserName);
                System.out.println("Ticket purchased.");
                ticketFound = true;
                break;
            }
        }

        if (!ticketFound) {
            System.out.println("Ticket not found or already sold. Please choose another seat.");
        }
    }

    public static void displayShowReport(ArrayList<Ticket> tickets) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the show: ");
        String showName = scanner.nextLine();

        System.out.println("Report for show '" + showName + "':");

        for (Ticket ticket : tickets) {
            if (!ticket.isAvailable()) {
                System.out.println(ticket);
            }
        }
    }

    public static ArrayList<Ticket> loadTicketsFromFile(String ticket_data) {
        ArrayList<Ticket> tickets = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(ticket_data))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int ticketNumber = Integer.parseInt(parts[1]);
                String purchaserName = parts[2];
                int randomCode = Integer.parseInt(parts[3]);
                Ticket ticket = new Ticket(ticketNumber);
                if (!purchaserName.equals("") || randomCode != 0) {
                    ticket.sellTicket(purchaserName);
                }
                tickets.add(ticket);
            }
        } catch (IOException e) {
            System.out.println("Error reading from the file: " + e.getMessage());
        }

        return tickets;
    }

    public static void saveTicketsToFile(ArrayList<Ticket> tickets, String ticket_data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ticket_data))) {
            for (Ticket ticket : tickets) {
                writer.write("ShowName," + ticket.getTicketNumber() + "," + ticket.getPurchaserName() + "," + ticket.getRandomCode());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to the file: " + e.getMessage());
        }
    }
}
