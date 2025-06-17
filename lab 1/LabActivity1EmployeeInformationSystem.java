// YRAY, ZACARIAS N.
// BSCS 1A

import java.util.Scanner; 

class LabActivity1EmployeeInformationSystem {
  public static void main(String[] args) {
    Scanner myScan = new Scanner(System.in);
    String userName;
    String lastName;
    int age;
    double hours;
    double wage;

    System.out.print("Enter your first name: "); 
    userName = myScan.nextLine();   

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

    System.out.println("Employee Information\n---------------------");
    System.out.println("Fullname is : "+userName.toUpperCase()+" "+lastName.toUpperCase());    
    System.out.println("Age is      : " + age + " years old");
    System.out.println("Daily salary: " + totalWage);

    myScan.close(); 
  }
}