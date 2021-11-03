import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

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
        final var tmp = Files.readAllLines(Paths.get("accounts.txt"));
        accounts = new ArrayList<Account>(tmp.size());
        String[] strs;
        for (final var s : tmp) {
            strs = s.split(" ");
            final var name = strs[2] + ' ' + strs[3];
            final float balance = Float.parseFloat(strs[4]);
            final int id = Integer.parseInt(strs[0]);
            accounts.add(
                switch (strs[1]) {
                    case "C" -> new CheckingAccount(balance, id, name, Integer.parseInt(strs[6]), Integer.parseInt(strs[5]));
                    case "S" -> new SavingsAccount(balance, id, name);
                    default -> throw new IllegalArgumentException("Unexpected value: " + strs[1]);
                }
            );
        }
    }

    public static void parseBatches() throws IOException {
        final var lines = Files.readAllLines(Paths.get("batch.txt"));
        for (final var line : lines) {
            final var strs = line.split(" ");
            final int id = Integer.parseInt(strs[1]);
            final var tmp = accounts.parallelStream().filter(a -> a.getNumber() == id).findAny();
            switch (strs[0]) {
                case "W": {
                    final String name = strs[3] + " " + strs[4];
                    final float amt = Float.parseFloat(strs[2]);
                    tmp.ifPresentOrElse(val -> {
                        val = processWithdrawal(val, amt, name);
                    }, () -> System.out.println("Account " + id + " not found."));
                }
                    break;
                case "D": {
                    final float amt = Float.parseFloat(strs[2]);
                    tmp.ifPresentOrElse(val -> {
                        val = processDeposit(val, amt);
                    }, () -> System.out.println("Account " + id + " not found."));
                }
                    break;
                case "C": {
                    final String name = strs[2] + " " + strs[3];
                    tmp.ifPresentOrElse(val -> {
                        val = processClose(val, name);
                    }, () -> System.out.println("Account " + id + " not found."));
                }
                    break;

                case "T": {
                    final String name = strs[4] + " " + strs[5];
                    final float amt = Float.parseFloat(strs[3]);
                    final int destId = Integer.parseInt(strs[2]);
                    var p = accounts.stream().filter(a -> a.getNumber() == destId).findAny();
                    if (p.isPresent() && tmp.isPresent()) {
                        processTransfer(tmp.get(), p.get(), amt, name);
                    }
                }
                    break;
            }
        }
    }

    public static void writeAccounts() throws FileNotFoundException {
        try (final var write = new PrintWriter(new FileOutputStream("accounts.txt", false))) {
            for (final var a : accounts) {
                final char type = a instanceof CheckingAccount ? 'C' : 'S';
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
