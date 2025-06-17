// YRAY, ZACARIAS N.
// BSCS 1A

import java.util.Scanner; 

class LabActivity2EmployeeInformationSystemPart2 {
  public static void main(String[] args) {
    Scanner myScan = new Scanner(System.in);
    String firstName;
    String lastName;
    int age;
    double hours;
    double wage;

    System.out.print("Enter your first name: "); 
    firstName = myScan.nextLine();   

    System.out.print("Enter your last name: ");
    lastName = myScan.nextLine();

    System.out.print("Enter your age: ");
    age = myScan.nextInt();
    myScan.nextLine(); 

    System.out.print("Enter hours worked: ");
    hours = myScan.nextDouble();
    myScan.nextLine(); 

    System.out.print("Enter hourly wage: ");
    wage = myScan.nextDouble();
    myScan.nextLine(); 

    double totalWage = hours * wage;
    double weeklyWage = totalWage * 5; 
    double monthlyWage = weeklyWage * 4; 
    double yearlyWage = monthlyWage * 12; 
    double netYearlyWage = yearlyWage * 0.32;

    System.out.println("\nEmployee Information\n---------------------");
    System.out.println("Full Name:                    " + lastName.toUpperCase() + ", " + firstName.toUpperCase());    
    System.out.println("Age:                          " + age + " years old");
    System.out.println("Years to Retirement:          65 years");
    System.out.printf("Daily Salary:                          Php %.2f\n", totalWage);
    System.out.printf("Weekly Salary:                         Php %.2f\n", weeklyWage);
    System.out.printf("Monthly Salary:                        Php %.2f\n", monthlyWage);
    System.out.printf("Gross Yearly Salary:                   Php %.2f\n", yearlyWage);
    System.out.printf("Net Yearly Salary:                     Php %.2f\n", netYearlyWage);

    myScan.close(); 
  }
}