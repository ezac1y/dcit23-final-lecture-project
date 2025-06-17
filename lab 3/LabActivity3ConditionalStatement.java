// YRAY, ZACARIAS
// BSCS 1A

import java.util.Scanner;

class LabActivity3ConditionalStatement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String firstName;
        String lastName;
        int age;
        double hoursWorked;
        double hourlyWage;
        String roleCode;

        while (true) {
            System.out.print("Enter your first name: ");
            firstName = sc.nextLine();

            System.out.print("Enter your last name: ");
            lastName = sc.nextLine();

            System.out.print("Enter your age: ");
            age = sc.nextInt();

            if (age <= 17) {
                System.out.println("\nMinors are not allowed");
                sc.nextLine(); 
                continue; 
            } 
            if (age > 65) {
                System.out.println("\nSenior Citizens are not allowed");
                sc.nextLine(); 
                continue; 
            }

            System.out.print("Enter hours worked: ");
            hoursWorked = sc.nextDouble();

            if (hoursWorked <= 0) {
                System.out.println("\nWrong input on daily work hours");
                sc.nextLine();
                continue; 
            }

            if (hoursWorked > 24) {
                System.out.println("\nNumber of hours worked cannot exceed 24 hours");
                sc.nextLine();
                continue; 
            }

            System.out.print("Enter hourly wage: ");
            hourlyWage = sc.nextDouble();

            System.out.print("Enter role code (1-Manager, 2-Supervisor, 3-Staff, 4-Intern): ");
            roleCode = sc.next();

            double totalSalary = hoursWorked * hourlyWage;
            double weeklySalary = totalSalary * 5;
            double monthlySalary = weeklySalary * 4;
            double yearlySalary = monthlySalary * 12;
            double netYearlyWage;

            if (yearlySalary > 250000) {
                netYearlyWage = yearlySalary - (yearlySalary * 0.32) - 1500;
            } else {
                netYearlyWage = yearlySalary - 1500;
            }

            System.out.println("\nEmployee Information\n---------------------");
            System.out.println("Full Name:             " + lastName.toUpperCase() + ", " + firstName.toUpperCase());
            System.out.println("Age:                   " + age + " years old");
            System.out.println("Position:              " + switch (roleCode) {
                case "1" -> "Manager";
                case "2" -> "Supervisor";
                case "3" -> "Staff";
                case "4" -> "Intern";
                default -> "Undefined";
            });
            System.out.printf("Daily Salary:          Php %.2f\n", totalSalary);
            System.out.printf("Weekly Salary:         Php %.2f\n", weeklySalary);
            System.out.printf("Monthly Salary:        Php %.2f\n", monthlySalary);
            System.out.printf("Gross Yearly Salary:   Php %.2f\n", yearlySalary);
            System.out.printf("Net Yearly Salary:     Php %.2f\n", netYearlyWage);

            break; 
        }

        sc.close();
    }
}