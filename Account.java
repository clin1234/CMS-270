/**
 * Lab 5
 * @author Alex Billini
 * @since 10/6/2021
 */
public abstract class Account {
    private float balance;
    private String owner;

    public Account(float balance, String owner) {
        this.balance = balance;
        this.owner = owner;
    }

    // Getters and setters

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    // Will be overridden in subclasses

    public abstract void deposit(float amount);
    public abstract void withdraw(float amount);
}
