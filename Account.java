/**
 * Assignment 2
 * 
 * @author Charlie Lin
 * @since 11/3/2021
 */
public abstract class Account {
    private float balance;
    private int number;
    private String owner;

    public Account(float balance, int number, String owner) {
        this.balance = balance;
        this.number = number;
        this.owner = owner;
    }

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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public abstract void deposit(float amount);

    public abstract void withdraw(float amount);

    // Transfers do not use a check (in case of checking accounts), so this kludge is needed.
    public void transfer(Account a, float amount) {
        withdraw(amount);
        a.deposit(amount);
    }
}
