public class SavingsAccount extends Account {
    boolean feeCharged = false;

    public SavingsAccount(float balance, int number, String owner) {
        super(balance, number, owner);
    }

    public void withdraw(float amount) {
        if (getBalance() - amount < 150)
            if (!feeCharged) {
                setBalance(getBalance() - 30);
                feeCharged = true;
            }
        setBalance(getBalance() - amount);
    }

    public void deposit(float amount) {
        setBalance(getBalance() + amount);
    }
}
