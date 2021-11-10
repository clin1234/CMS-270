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

	// Constructor

	public Account(double balance, int number, String owner) {
		this.balance = balance;
		this.number = number;
		this.owner = owner;
	}

	// Getters and setters

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

	// Transaction methods

	public abstract void deposit(double amount);

	public abstract void withdraw(double amount);

	public void transfer(Account a, double amount) {
		// Lowering balance in a transfer obeys the same rules as withdrawals, hence the
		// call to withdraw()
		withdraw(amount);
		// Recipient checking account does not incur a check when receiving transfer.
		// This works for any type of recipient account.
		a.setBalance(a.getBalance() + amount);
	}

	public boolean isClosable() {
		return balance >= 0;
	}

	public void close() {
		if (balance >= 0) {
			owner = null;
			number = 0;
			balance = 0;
		} else
			System.err.println(owner + String.format(" cannot close Account %d", getNumber()));
	}

	// Auxiliary methods

	public String toString() {
		return String.format("Owner: %s ID: %d Balance: %.2f", owner, number, balance);
	}
}
