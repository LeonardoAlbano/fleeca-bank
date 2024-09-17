package fleeca_bank;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AccountApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Account> accounts = new ArrayList<>();

        Account accountPredefined = new Account("ADM - Fleeca Bank", LocalDate.of(1000, 5,5), 1_000_000);
        accountPredefined.setNumberAccount("000006");
        accountPredefined.setNumberAgency("003");
        accounts.add(accountPredefined);

        Account accountActive = null;

        while (true) {
            System.out.println("\n=== Main initial ===");
            System.out.println("1. Login");
            System.out.println("2. Create Account");
            System.out.println("3. Quit");

            int optionInitial = scanner.nextInt();
            scanner.nextLine();

            switch (optionInitial) {
                case 1:
                    System.out.println("Enter account number:");
                    String numberAccountLogin = scanner.next();

                    System.out.println("Enter agency number:");
                    String numberAgencyLogin = scanner.next();

                    System.out.println("Enter your born (formation: yyyy-mm-dd):");
                    String bornLoginString = scanner.next();
                    LocalDate bornLogin = LocalDate.parse(bornLoginString);

                    boolean accountFound = false;

                    for (Account account : accounts) {
                        if(numberAccountLogin.equals(account.getNumberAccount()) &&
                            numberAgencyLogin.equals(account.getNumberAgency()) &&
                            bornLogin.equals(account.getBorn())) {
                            accountActive = account;
                            accountFound = true;
                            break;
                        }
                    }

                    if (accountFound) {
                        System.out.println("Login successfully! welcome!");

                        while (true) {
                            System.out.println("\n=== Choose an operation ===");
                            System.out.println("1. Out Money");
                            System.out.println("2. Transfer");
                            System.out.println("3. Deposit");
                            System.out.println("4. Cancel Account");
                            System.out.println("5. Check balance");
                            System.out.println("6. Return to home");

                            int option = scanner.nextInt();
                            scanner.nextLine();

                            switch (option) {
                                case 1:
                                    System.out.println("Enter the amount to withdraw");
                                    double valueSake = scanner.nextDouble();
                                    accountActive.out(valueSake);
                                    accountActive.extratcAccount();
                                    break;

                                case 2:
                                    System.out.println("Enter the target account number:");
                                    String numberAccountTarget = scanner.nextLine();

                                    System.out.println("Enter the destination branch number:");
                                    String numberAgencyTarget = scanner.nextLine();

                                    Account accountTarget = null;

                                    for (Account account : accounts) {
                                        if(numberAccountTarget.equals(account.getNumberAccount()) &&
                                        numberAgencyTarget.equals(account.getNumberAgency())) {
                                            accountTarget = account;
                                            break;
                                        }
                                    }

                                    if (accountTarget != null) {
                                        System.out.println("Enter value for transfer:");
                                        double valueTransfer = scanner.nextDouble();
                                        accountActive.transfer(accountTarget, valueTransfer);
                                        accountActive.extratcAccount();
                                        accountTarget.extratcAccount();
                                    } else {
                                        System.out.println("Transfer failed: Registration not found.");
                                    }
                                    break;

                                case 3:
                                    System.out.println("Enter a value deposit:");
                                    double valueDeposit = scanner.nextDouble();
                                    accountActive.deposit(valueDeposit);
                                    accountActive.extratcAccount();
                                    break;

                                case 4:
                                    System.out.println("Enter the justification for a cancel account");
                                    String justification = scanner.nextLine();
                                    accountActive.cancelAccount(justification);
                                    accounts.remove(accountActive);
                                    accountActive = null;
                                    System.out.println("Account cancel successfully. Return to home...");
                                    break;

                                case 5:
                                    accountActive.extratcAccount();
                                    break;

                                case 6:
                                    break;

                                default:
                                    System.out.println("Option invalid, try again.. ");
                            }

                            if (option == 6 || accountActive == null ) {
                                break;
                            }
                        }
                    } else {
                        System.out.println("Error login. account not found !");
                    }
                    break;

                case 2:
                    System.out.println("Enter your name:");
                    String nameClient = scanner.nextLine();

                    System.out.println("Enter your born (example: YYYY-MM-DD):");
                    String bornString = scanner.nextLine();
                    LocalDate born = LocalDate.parse(bornString);

                    System.out.println("Enter your balance initial: ");
                    double balanceInitial = scanner.nextDouble();

                    accountActive = new Account(nameClient, born, balanceInitial);
                    accounts.add(accountActive);
                    System.out.println("Account create successfully");
                    System.out.println("Number your Account: " + accountActive.getNumberAccount());
                    System.out.println("Number your Agency: " + accountActive.getNumberAgency());
                    break;

                case 3:
                    System.out.println("Quit...");
                    return;

                default:
                    System.out.println("Option invalid, try again");

            }
        }

    }
}
