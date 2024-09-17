package fleeca_bank;

import java.time.LocalDate;
import java.util.Random;

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

    public boolean transfer( Account destination, double value ) {
        if (out(value)) {
            destination.deposit(value);
            System.out.println("Transfer completed successfully !");
            return true;
        } else {
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
        System.out.println("Balance Now: " + balance);
    }

}
