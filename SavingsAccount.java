/**
 * Assignment 2
 * 
 * @author Charlie Lin
 * @since 11/3/2021
 */
public class SavingsAccount extends Account {
    boolean feeCharged = false;

    public SavingsAccount(double balance, int number, String owner) {
        super(balance, number, owner);
    }

    public void withdraw(double amount) {
        if (getBalance() - amount < 150)
            /*
             * Maintainance fee is only charged once per month, and since the batch file
             * contains transactions for one month, check if the fees has been charged
             * already.
             */
            if (!feeCharged) {
                setBalance(getBalance() - 30);
                feeCharged = true;
            }
        setBalance(getBalance() - amount);
    }

    public void deposit(double amount) {
        setBalance(getBalance() + amount);
    }

    public void close() {
        super.close();
        feeCharged = false;
    }

    public String toString() {
        return "Type: savings " + super.toString() + " Fee charged? " + feeCharged;
    }
}
