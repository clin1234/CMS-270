/**
 * Lab 5
 * @author Alex Billini
 * @since 10/6/2021
 */

public class SavingsAccount extends Account {
    // No additional data members defined in SavingsAccount, so only call the superclass' constructor
    public SavingsAccount(float balance, String owner) {
        super(balance, owner);
    }

    public void withdraw(float amount) {
        if (getBalance() - amount < 150)
            System.out.printf("You may not withdraw %.2f, because that will drop the balance below 150.\n", amount);
        else
            setBalance(getBalance() - amount);
    }

    public void deposit(float amount) {
        setBalance(getBalance() + amount);
    }
}
