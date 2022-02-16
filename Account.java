package Task;

//************************************************************
//Account.java
//
//A bank account class with methods to deposit to, withdraw from,
//change the name on, and get a String representation
//of the account.
//************************************************************
public class Account {
    private double balance;
    private String name;
    private long acctNum;
    private static int numAccounts=0;

    // -------------------------------------------------
    // Constructor -- initializes balance, owner, and account number
    // -------------------------------------------------
    public Account(double initBal, String owner, long number) {
        balance = initBal;
        name = owner;
        acctNum = number;
        numAccounts++;
    }

    // -------------------------------------------------
    // Constructor -- initializes balance, owner and a random account number
    // -------------------------------------------------
    public Account(double initBal, String owner) {
        balance = initBal;
        name = owner;
        acctNum = (int) (Math.random() * Integer.MAX_VALUE);
        numAccounts++;
    }

    // -------------------------------------------------
    // Constructor -- initializes balance equal 0, owner and a random account
    // number
    // -------------------------------------------------
    public Account(String owner) {
        balance = 0;
        name = owner;
        acctNum = (int) (Math.random() * Integer.MAX_VALUE);
        numAccounts++;
    }

    public static int numAccount() {
        return numAccounts;
    }

    public String getName() {
        return name;
    }

    public long getAcctNum() {
        return acctNum;
    }

    // -------------------------------------------------
    // Returns balance.
    // -------------------------------------------------
    public double getBalance() {
        return balance;
    }

    // -------------------------------------------------
    // Checks to see if balance is sufficient for withdrawal.
    // If so, decrements balance by amount; if not, prints message.
    // -------------------------------------------------
    public void withdraw(double amount) {
        if (balance >= amount)
            balance -= amount;
        else
            System.out.println("Insufficient funds");
    }

    // -------------------------------------------------
    // takes a fee and deducts that fee from the account.
    // Checks to see if balance is sufficient for withdrawal.
    // If so, decrements balance by amount; if not, prints message.
    // -------------------------------------------------
    public void withdraw(double amount, double fee) {

        if (balance >= amount + fee)
            balance = balance - amount - fee;
        else
            System.out.println("Insufficient funds");
    }

    // -------------------------------------------------
    // Adds deposit amount to balance.
    // -------------------------------------------------

    public void deposit(double amount) {
        balance += amount;
    }

    // -------------------------------------------------
    // Returns a string containing the name, account number, and balance.
    // -------------------------------------------------
    public String toString() {
        return "Name:" + name + "\nAccount Number: " + acctNum + "\nBalance: "
                + balance;
    }


    public void close() {
        name += "-CLOSED";
        balance = 0;
        numAccounts--;
    }

}