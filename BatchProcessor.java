import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Assignment 2
 * 
 * @author Charlie Lin
 * @since 11/3/2021
 */

public class BatchProcessor {
	static ArrayList<Account> accounts;

	public static void main(String[] args) {
		try {
			parseAccounts();
			parseBatches();
			writeAccounts();
			// Handle exceptions in main() to simplify code flow.
			// Also, FileNotFoundException is a subclass of IOException,
			// so this works
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* Files.readAllLines() could throw a checked exception. To handle this,
	annotate the method in its signature, in order to handle it in main().*/
	public static void parseAccounts() throws IOException {
		final var lines = Files.readAllLines(Paths.get("accounts.txt"));
		// Reserve capacity of ArrayList equal to the number of lines in accounts.txt
		accounts = new ArrayList<>(lines.size());
		String[] fields;
		for (final var line : lines) {
			fields = line.split(" ");
			// Owner
			final var name = fields[2] + ' ' + fields[3];
			// Account balance
			final float balance = Float.parseFloat(fields[4]);
			// Account number
			final int id = Integer.parseInt(fields[0]);
			// Account type
			final String type = fields[1];
			/*
			 * A switch expression, indicated by the arrow after each case label, yields a
			 * returnable value corresponding to the label, and no fall-through occurs. In
			 * this case, this simplifies adding an instance of a subclass of Account to the
			 * ArrayList.
			 */
			accounts.add(switch (type) {
			/*
			 * Fifth and sixth fields correspond to the number of checks used, and the
			 * maximum number of checks allowed.
			 */
			case "C" -> new CheckingAccount(balance, id, name, Integer.parseInt(fields[6]),
					Integer.parseInt(fields[5]));
			case "S" -> new SavingsAccount(balance, id, name);
			// Required in a switch expression. Throw an exception just in case.
			default -> throw new IllegalArgumentException("Unexpected value: " + fields[1]);
			});
		}
	}

	/* Files.readAllLines() could throw a checked exception. To handle this,
	annotate the method in its signature, in order to handle it in main().*/
	public static void parseBatches() throws IOException {
		final var lines = Files.readAllLines(Paths.get("batch.txt"));
		for (final var line : lines) {
			final var fields = line.split(" ");
			// Account number
			final int id = Integer.parseInt(fields[1]);
			/*
			 * One-liner to find an Account object corresponding to the Account number. This
			 * line does the following:
			 * 1. Create a stream on the ArrayList accounts
			 * 2. Search an account matching the account number (first field) on the stream
			 * 3. Gets the Account object that satisfies step 2.
			 */
			var sourceAct = accounts.parallelStream().filter(a -> a.getNumber() == id).findFirst().get();
			// First field is type of transaction
			switch (fields[0]) {
				case "W": {
					final String name = fields[3] + " " + fields[4];
					final float amount = Float.parseFloat(fields[2]);
					sourceAct = processWithdrawal(sourceAct, amount, name);
				}
					break;
				case "D": {
					final float amount = Float.parseFloat(fields[2]);
					sourceAct = processDeposit(sourceAct, amount);
				}
					break;
				case "C": {
					final String name = fields[2] + " " + fields[3];
					sourceAct = processClose(sourceAct, name);
				}
					break;

				case "T": {
					final String name = fields[4] + " " + fields[5];
					final float amount = Float.parseFloat(fields[3]);
					// Account number of receiving account
					final int destId = Integer.parseInt(fields[2]);
					// One-liner to find an Account object corresponding to the destination account
					// number
					var destAcct = accounts.stream().filter(a -> a.getNumber() == destId).findAny();
					if (destAcct.isPresent()) {
						processTransfer(sourceAct, destAcct.get(), amount, name);
					}
				}
					break;
			}
		}
	}

	/* FileOutputStream's constructor could throw a checked exception. To handle this,
	annotate the method in its signature, in order to handle it in main().*/
	public static void writeAccounts() throws FileNotFoundException {
		/*
		 * Pass in a FileOutStream object in PrintWriter's constructor, because
		 * FileOutputStream's constructor has a boolean parameter that either appends
		 * writes to a file (true), or overwrites it (false)
		 */
		try (final var write = new PrintWriter(new FileOutputStream("accounts.txt", false))) {
			for (final var a : accounts) {
				// Assign to the character 'C' if the account is a checking one, 'S' otherwise
				final char type = a instanceof CheckingAccount ? 'C' : 'S';
				// Appending to a StringBuilder instead of a String is less costly resource-wise
				final var tmpBuilder = new StringBuilder(
						String.format("%d %c %s %.2f", a.getNumber(), type, a.getOwner(), a.getBalance()));
				if (a instanceof CheckingAccount c)
					tmpBuilder.append(String.format(" %d %d", c.getChecksUsed(), c.getMonthlyCheckLimit()));
				write.println(tmpBuilder.toString());
			}
		}

	}

	public static Account processDeposit(Account a, double Amt) {
		a.deposit(Amt);
		// System.out.println(a.toString());
		return a;
	}

	public static Account processWithdrawal(Account a, double Amt, String owner) {
		if (!a.getOwner().equals(owner))
			System.err.println(owner + String.format(" cannot withdraw %.2f from Account %d", Amt, a.getNumber()));
		else
			a.withdraw(Amt);
		// System.out.println(a.toString());
		return a;
	}

	public static void processTransfer(Account a, Account b, double Amt, String owner) {
		if (!a.getOwner().equals(owner)) {
			System.err.println(owner + String.format(" cannot transfer from Account %d", a.getNumber()));
		}
		a.transfer(b, Amt);
		// System.out.println(a.toString());
	}

	public static Account processClose(Account a, String owner) {
		if (!a.getOwner().equals(owner)) {
			System.err.println(owner + String.format(" cannot close Account %d", a.getNumber()));
			return a;
		} else {
			a.close();
			// System.out.println(a.toString());
			// Removes account from ArrayList, then store removed account
			var b = accounts.remove(accounts.indexOf(a));
			// Sanity check
			assert !accounts.contains(b);
			return b;
		}
	}
}
