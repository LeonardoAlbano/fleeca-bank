package fleeca_bank;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

//Defining the class
public class Account {

    private String numberAccount;
    private String numberAgency;
    private String nameClient;
    private LocalDate born;
    private double balance;

    // Create Object class, This constructor accepts 3 parameters: ( nameClient, born, balanceInitial )
    public Account(String nameClient, LocalDate born, double balanceInitial) {
        this.numberAccount = generateRandomNumber(6); //method generate number
        this.numberAgency = generateRandomNumber(3);
        this.nameClient = nameClient;
        this.born = born;
        this.balance = balanceInitial;
    }

    public Account(String numberAccount, String numberAgency, String nameClient, LocalDate born, double balanceInitial) {
        this.numberAccount = numberAccount;
        this.numberAgency = numberAgency;
        this.nameClient = nameClient;
        this.born = born;
        this.balance = balanceInitial;
    }

    private String generateRandomNumber(int amountDigits) {

        Random random = new Random();
        StringBuilder number = new StringBuilder();
        for ( int i = 0; i < amountDigits; i++ ) {
            number.append(random.nextInt(10));
        }

        return number.toString();
    }

    public String getNumberAccount() {
        return numberAccount;
    }

    public void setNumberAccount(String numberAccount) {
        this.numberAccount = numberAccount;

    }

    public String getNumberAgency() {
        return numberAgency;
    }

    public void setNumberAgency(String numberAgency) {
        this.numberAgency = numberAgency;
    }

    public String getNameClient() {
        return nameClient;
    }

    public LocalDate getBorn() {
        return born;
    }

    // public method that makes a withdrawal from the account.
    public boolean out(double value) {
        if ( value > balance ) {
            System.out.println("Insufficient balance to withdraw !");
            return false;
        } else {
            balance -= value;
            return true;
        }
    }


    public boolean transfer(Account destination, double value) {
        Scanner scanner = new Scanner(System.in);

        //Format the value to currency format
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        String formattedValue = currencyFormatter.format(value);

        // Show confirmation message
        System.out.println("You are about to transfer " + formattedValue + " to " + destination.getNameClient() + ".");
        System.out.println("Do you want to proceed? (yes/no)");

        String confirmation = scanner.nextLine().trim().toLowerCase();

        // Check if the user confirmed
        if (confirmation.equals("yes")) {
            if (out(value)) {
                destination.deposit(value);
                System.out.println("Transfer completed successfully!");
                return true;
            } else {
                System.out.println("Transfer failed: Insufficient funds.");
                return false;
            }
        } else {
            System.out.println("Transfer cancelled.");
            return false;
        }
    }

    public void deposit(double value) {
        balance += value;

    }

    public void cancelAccount(String justification) {
        balance = 0;
        System.out.println("Cancel account. justification:" + justification);
    }

    public void extratcAccount() {

        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        String formattedBalance = currencyFormatter.format(balance);

        System.out.println("Current balance: " + formattedBalance);

    }

}
