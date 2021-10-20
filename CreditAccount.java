public class CreditAccount extends Account {
    private int creditLimit;

    public CreditAccount(float balance, String owner) {
        super(balance, owner);
    }

    public CreditAccount(float balance, String owner, int creditLimit) {
        this(balance, owner);
        this.creditLimit = creditLimit;
    }

    public int getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(int creditLimit) {
        this.creditLimit = creditLimit;
    }

    // Pay amount, which will decrease account's balance
    public void withdraw(float amount) {
        if (amount >= getBalance())
            setBalance(0);
        else
            setBalance(getBalance() - amount);
    }

    // Credit amount, which will increase account's balance
    public void deposit(float amount) {
        if (amount + getBalance() > creditLimit)
            System.out.printf("Crediting %.2f will exceed credit limit.\n", amount);
        else
            setBalance(getBalance() + amount);
    }

}
