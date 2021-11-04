/**
 * Assignment 2
 * 
 * @author Charlie Lin
 * @since 11/3/2021
 */
public abstract class Account {
    private double balance;
    private int number;
    private String owner;

    public Account(double balance, int number, String owner) {
        this.balance = balance;
        this.number = number;
        this.owner = owner;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public abstract void deposit(double amount);

    public abstract void withdraw(double amount);

    public void transfer(Account a, double amount) {
        withdraw(amount);
        if (a instanceof CheckingAccount ca)
            ca.setBalance(ca.getBalance() + amount);
    }

    public void close() {
        if (balance > 0) {
            owner = null;
            number = 0;
            balance = 0;
        }
    }

    public String toString() {
        return "Owner: %s ID: %d Balance: %.2f".formatted(owner, number, balance);
    }
}
