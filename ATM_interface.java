import java.util.Scanner;

// Step 4: Class representing the user's bank account
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

// Step 1 & 5: Class representing the ATM machine connected to the bank account
class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    // Step 2 & 7: User interface menu loop
    public void start() {
        while (true) {
            System.out.println("\n========== ATM MENU ==========");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    handleCheckBalance();
                    break;
                case 2:
                    handleDeposit();
                    break;
                case 3:
                    handleWithdraw();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Step 3 & 7: Implementations for each ATM function
    private void handleCheckBalance() {
        System.out.printf("Your current balance is: $%.2f%n", account.getBalance());
    }

    private void handleDeposit() {
        System.out.print("Enter deposit amount: $");
        double amount = scanner.nextDouble();

        // Step 6: Validate positive numbers
        if (amount > 0) {
            account.deposit(amount);
            System.out.printf("Successfully deposited $%.2f%n", amount);
            handleCheckBalance();
        } else {
            System.out.println("Error: Deposit amount must be positive.");
        }
    }

    private void handleWithdraw() {
        System.out.print("Enter withdrawal amount: $");
        double amount = scanner.nextDouble();

        // Step 6: Validate positive numbers
        if (amount <= 0) {
            System.out.println("Error: Withdrawal amount must be positive.");
            return;
        }

        // Step 6 & 7: Validate sufficient funds and perform transaction
        if (account.withdraw(amount)) {
            System.out.printf("Successfully withdrew $%.2f%n", amount);
            handleCheckBalance();
        } else {
            System.out.println("Error: Insufficient balance for this withdrawal.");
        }
    }
}

// Main class to run the application
public class ATM_interface {
    public static void main(String[] args) {
        // Initialize account with a $500.00 starting balance
        BankAccount userAccount = new BankAccount(500.00);
        ATM atmMachine = new ATM(userAccount);
        
        // Start the ATM application
        atmMachine.start();
    }
}