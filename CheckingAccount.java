/**
 * Assignment 2
 * 
 * @author Charlie Lin
 * @since 11/3/2021
 */
public class CheckingAccount extends Account {
    private int monthlyCheckLimit;
    private int checksUsed;
    private String ERROR = "Maximum number of checks (%d) for account %d reached".formatted(monthlyCheckLimit,
            getNumber());

    // Getters and setters

    public int getMonthlyCheckLimit() {
        return monthlyCheckLimit;
    }

    public void setMonthlyCheckLimit(int monthlyCheckLimit) {
        this.monthlyCheckLimit = monthlyCheckLimit;
    }

    public int getChecksUsed() {
        return checksUsed;
    }

    public void setChecksUsed(int checksUsed) {
        this.checksUsed = checksUsed;
    }

    // Constructor

    public CheckingAccount(double balance, int number, String owner, int limit, int used) {
        super(balance, number, owner);
        monthlyCheckLimit = limit;
        checksUsed = used;
    }

    // Overriden methods

    @Override
    public void deposit(double amount) {
        if (checksUsed == monthlyCheckLimit) {
            System.err.println(ERROR);
            return;
        }
        setBalance(getBalance() + amount);
        checksUsed++;
    }

    @Override
    public void withdraw(double amount) {
        if (checksUsed == monthlyCheckLimit) {
            System.err.println(ERROR);
            return;
        }
        setBalance(getBalance() - amount);
        checksUsed++;
    }

    public void close() {
        super.close();
        checksUsed = 0;
        monthlyCheckLimit = 0;
    }

    public String toString() {
        return "Type: checking " + super.toString() + " Max: " + monthlyCheckLimit + " Used: " + checksUsed;
    }
}
