/**
 * Assignment 2
 * @author Charlie Lin
 * @since 11/3/2021
 */
public class CheckingAccount extends Account {
    private final int monthlyCheckLimit;
    private int checksUsed;

    // Getters and setters

    public int getMonthlyCheckLimit() {
        return monthlyCheckLimit;
    }

    public int getChecksUsed() {
        return checksUsed;
    }

    public void setChecksUsed(int checksUsed) {
        this.checksUsed = checksUsed;
    }

    // Constructor

    public CheckingAccount(float balance, int number, String owner, int monthlyCheckLimit, int checksUsed) {
        super(balance, number, owner);
        this.monthlyCheckLimit = monthlyCheckLimit;
        this.checksUsed = checksUsed;
    }

    // Overriden methods
    
    @Override
    public void deposit(float amount) {
        if (checksUsed == monthlyCheckLimit) {
            System.err.println("Maximum number of checks (%d) for account %d reached".formatted(monthlyCheckLimit, getNumber()));
            return;
        }
        setBalance(getBalance() + amount);
        checksUsed++;
    }

    @Override
    public void withdraw(float amount) {
        if (checksUsed == monthlyCheckLimit) {
            System.err.println("Maximum number of checks (%d) for account %d reached".formatted(monthlyCheckLimit, getNumber()));
            return;
        }
        setBalance(getBalance() - amount);
        checksUsed++;
    }
}
