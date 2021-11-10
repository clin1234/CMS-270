/**
 * Assignment 2
 * 
 * @author Charlie Lin
 * @since 11/3/2021
 */
public class SavingsAccount extends Account {
	private boolean feeCharged;

    // Constructor

	public SavingsAccount(double balance, int number, String owner) {
		super(balance, number, owner);
		feeCharged = false;
	}

    // Overriden transactional methods

	public void withdraw(double amount) {
        if (amount > getBalance()) {
            System.err.println(String.format("Withdrawing %.2f exceeds current savings balance of %.2f. Stop.",
                amount, getBalance()));
            return;
        }
		if (getBalance() - amount < 150)
			/*
			 * Maintainance fee is only charged once per month, and since the batch file
			 * contains transactions for one month, check if the fee has been charged
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

    @Override
	public void close() {
		super.close();
		feeCharged = false;
	}

    // Auxiliary methods

	public String toString() {
		return "Type: savings " + super.toString() + " Fee charged? " + feeCharged;
	}
}
