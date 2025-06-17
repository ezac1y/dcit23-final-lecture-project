import java.util.Scanner;

public class MyMidtermLabExam {

    // Define a Ticket class to store ticket details
    static class Ticket {
        String description;
        String urgency;
        String status;

        Ticket(String description, String urgency) {
            this.description = description;
            this.urgency = urgency;
            this.status = "Pending"; // Default status
        }

        @Override
        public String toString() {
            return "[" + urgency + "] " + description + " - Status: " + status;
        }
    }

    // Array to store tickets
    static Ticket[] tickets = new Ticket[5];
    static int ticketCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("=== Main Menu ===");
            System.out.println("1. Add Ticket");
            System.out.println("2. Update Ticket Status");
            System.out.println("3. Show All Tickets");
            System.out.println("4. Generate Report");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                scanner.next(); // Consume invalid input
                continue;
            }

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addTicket(scanner);
                    break;
                case 2:
                    updateTicketStatus(scanner);
                    break;
                case 3:
                    showTickets();
                    break;
                case 4:
                    generateReport();
                    break;
                case 5:
                    System.out.println("Exiting the system. Goodbye!");
                    System.exit(0); // Explicitly terminate the program
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        } while (choice != 5);

        scanner.close();
    }

    // Method to add a ticket
    public static void addTicket(Scanner scanner) {
        if (ticketCount >= tickets.length) {
            System.out.println("Ticket limit reached. Cannot add more tickets.");
            return;
        }

        System.out.print("Enter issue description: ");
        String description = scanner.nextLine();

        System.out.print("Enter urgency level (Low, Medium, High): ");
        String urgency = scanner.nextLine().trim(); // Trim to remove leading/trailing spaces

        // Normalize input to lowercase for comparison
        if (!urgency.equalsIgnoreCase("Low") && !urgency.equalsIgnoreCase("Medium") && !urgency.equalsIgnoreCase("High")) {
            System.out.println("Invalid urgency level. Ticket not added.");
            return;
        }

        tickets[ticketCount++] = new Ticket(description, urgency);
        System.out.println("Ticket added successfully!");
    }

    // Method to update ticket status
    public static void updateTicketStatus(Scanner scanner) {
        if (ticketCount == 0) {
            System.out.println("No tickets available to update.");
            return;
        }

        System.out.println("=== Update Ticket Status ===");
        showAllTickets();

        System.out.print("Enter the ticket number to update: ");
        int ticketNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (ticketNumber < 1 || ticketNumber > ticketCount) {
            System.out.println("Invalid ticket number. Please try again.");
            return;
        }

        Ticket ticket = tickets[ticketNumber - 1];

        if (ticket.status.equals("Resolved")) {
            System.out.println("This ticket is already resolved and cannot be updated.");
            return;
        }

        System.out.print("Enter new status (In Progress, Resolved): ");
        String newStatus = scanner.nextLine();

        if (!newStatus.equalsIgnoreCase("In Progress") && !newStatus.equalsIgnoreCase("Resolved")) {
            System.out.println("Invalid status. Ticket status not updated.");
            return;
        }

        ticket.status = newStatus;
        System.out.println("Ticket status updated successfully!");
    }

    // Method to show all tickets
    public static void showAllTickets() {
        if (ticketCount == 0) {
            System.out.println("No tickets to display.");
            return;
        }

        System.out.println("=== All Tickets ===");
        for (int i = 0; i < ticketCount; i++) {
            System.out.println((i + 1) + ". " + tickets[i]);
        }
    }
    
    // Method to show all tickets
    public static void showTickets() {
        if (ticketCount == 0) {
            System.out.println("No tickets to display.");
            return;
        }

        System.out.println("=== All Tickets ===");
        for (int i = 0; i < ticketCount; i++) {
            System.out.println((i + 1) + ". " + tickets[i]);
        }
    }

    public static void generateReport() {
        int pendingCount = 0;
        int resolvedCount = 0;

        for (int i = 0; i < ticketCount; i++) {
            if (tickets[i].status.equalsIgnoreCase("Resolved")) {
                resolvedCount++;
            } else {
                pendingCount++; // Includes both "Pending" and "In Progress"
            }
        }

        System.out.println("=== Ticket Report ===");
        System.out.println("Total Tickets: " + ticketCount);
        System.out.println("Pending Tickets (including In Progress): " + pendingCount);
        System.out.println("Resolved Tickets: " + resolvedCount);
    }
}