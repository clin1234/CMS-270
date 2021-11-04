import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Assignment 2
 * @author Charlie Lin
 * @since 11/3/2021
 */

public class BatchProcessor {
    static ArrayList<Account> accounts;

    public static void main(String[] args) {
        try {
            parseAccountsFile();
            parseBatches();
            writeAccounts();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void parseAccountsFile() throws IOException {
        final var lines = Files.readAllLines(Paths.get("accounts.txt"));
        accounts = new ArrayList<>(lines.size());
        String[] fields;
        for (final var line : lines) {
            fields = line.split(" ");
            final var name = fields[2] + ' ' + fields[3];
            final float balance = Float.parseFloat(fields[4]);
            final int id = Integer.parseInt(fields[0]);
            final String type = fields[1];
            /* A switch expression, indicated by the arrow after each case label, yields a returnable value
            corresponding to the label, and there's no fall-through. In this case, this simplifies
            adding an instance of a subclass of Account to the ArrayList.*/
            accounts.add(
                switch (type) {
                    /* Fifth and sixth fields correspond to the number of checks used,
                    and the maxmimum number of checks allowed. */
                    case "C" -> new CheckingAccount(balance, id, name, Integer.parseInt(fields[6]), Integer.parseInt(fields[5]));
                    case "S" -> new SavingsAccount(balance, id, name);
                    // Unlikely, but helpful in case of bad input
                    default -> throw new IllegalArgumentException("Unexpected value: " + fields[1]);
                }
            );
        }
    }

    public static void parseBatches() throws IOException {
        final var lines = Files.readAllLines(Paths.get("batch.txt"));
        for (final var line : lines) {
            final var fields = line.split(" ");
            final int id = Integer.parseInt(fields[1]);
            // One-liner to find an Account object corresponding to the Account number
            final var tmp = accounts.parallelStream().filter(a -> a.getNumber() == id).findAny();
            /* The tmp.ifPresentOrElse method ensures that in case no account has the id,
            the program errors out before executing any type of transaction. */
            switch (fields[0]) {
                case "W": {
                    final String name = fields[3] + " " + fields[4];
                    final float amt = Float.parseFloat(fields[2]);
                    tmp.ifPresentOrElse(val -> {
                        val = processWithdrawal(val, amt, name);
                    }, () -> System.out.println("Account " + id + " not found."));
                }
                    break;
                case "D": {
                    final float amt = Float.parseFloat(fields[2]);
                    tmp.ifPresentOrElse(val -> {
                        val = processDeposit(val, amt);
                    }, () -> System.out.println("Account " + id + " not found."));
                }
                    break;
                case "C": {
                    final String name = fields[2] + " " + fields[3];
                    tmp.ifPresentOrElse(val -> {
                        val = processClose(val, name);
                    }, () -> System.out.println("Account " + id + " not found."));
                }
                    break;

                case "T": {
                    final String name = fields[4] + " " + fields[5];
                    final float amt = Float.parseFloat(fields[3]);
                    final int destId = Integer.parseInt(fields[2]);
                    // One-liner to find an Account object corresponding to the destination account number
                    var destAcct = accounts.stream().filter(a -> a.getNumber() == destId).findAny();
                    if (destAcct.isPresent() && tmp.isPresent()) {
                        processTransfer(tmp.get(), destAcct.get(), amt, name);
                    }
                }
                    break;
            }
        }
    }

    public static void writeAccounts() throws FileNotFoundException {
        /* Pass in a FileOutStream object in PrintWriter's constructor, because 
        FileOutputStream's constructor has a boolean parameter that either appends writes to a file (true),
        or overwrites it (false) */
        try (final var write = new PrintWriter(new FileOutputStream("accounts.txt", false))) {
            for (final var a : accounts) {
                final char type = a instanceof CheckingAccount ? 'C' : 'S';
                // Appending to a StringBuilder instead of a String is less costly resource-wise
                var b = new StringBuilder("%d %c %s %.2f".formatted(a.getNumber(), type, a.getOwner(), a.getBalance()));
                if (a instanceof CheckingAccount c)
                    b.append(" %d %d".formatted(c.getChecksUsed(), c.getMonthlyCheckLimit()));
                write.println(b.toString());
            }
        }

    }

    public static Account processDeposit(Account a, double Amt) {
        a.deposit((float) Amt);
        return a;
    }

    public static Account processWithdrawal(Account a, double Amt, String owner) {
        if (!a.getOwner().equals(owner))
            System.err.println(owner + " cannot withdraw %.2f from Account %d".formatted(Amt, a.getNumber()));
        else
            a.withdraw((float) Amt);
        return a;
    }

    public static void processTransfer(Account a, Account b, double Amt, String owner) {
        if (!a.getOwner().equals(owner)) {
            System.err.println(owner + " cannot transfer from Account %d".formatted(a.getNumber()));
        }
        a.transfer(b, (float) Amt);
    }

    public static Account processClose(Account a, String owner) {
        if (!a.getOwner().equals(owner) || a.getBalance() < 0) {
            System.err.println(owner + " cannot close Account %d".formatted(a.getNumber()));
            return a;
        } else
            return accounts.remove(accounts.indexOf(a));
    }
}
