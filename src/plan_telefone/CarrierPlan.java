package plan_telefone;

import java.util.Scanner;

public class CarrierPlan {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Plans plans = new Plans();

        System.out.println("Please, enter your name: ");
        String name = scanner.nextLine();

        System.out.println("Hello " + name + "! Which plan would you like to choose?");
        System.out.println("1. Basic Plan: " + plans.basicPlan);
        System.out.println("2. Medium Plan: " + plans.mediumPlan);
        System.out.println("3. Gold Plan: " + plans.goldPlan);

        int choice = scanner.nextInt();
        String selectedPlan = "";

        switch (choice) {
            case 1:
                selectedPlan = "Basic Plan";
                break;

            case 2:
                selectedPlan = "Medium Plan";
                break;

            case 3:
                selectedPlan = "Gold Plan";
                break;

            default:
                System.out.println("Invalid choice.");
                System.exit(0);
        }

        System.out.println("Are you sure want to choose the " + selectedPlan + "? ( yes / no )");
        scanner.nextLine();
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("yes")) {
            System.out.println("Thank you, " + name + "! You have chosen the " + selectedPlan + ".");

        } else {
            System.out.println("Plan selection canceled. ");
        }

        scanner.close();
    }
}
