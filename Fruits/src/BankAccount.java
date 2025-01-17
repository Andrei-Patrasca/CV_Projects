import java.io.*;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private final File file;
    private final ReentrantLock lock = new ReentrantLock();

    public BankAccount(String filePath) throws IOException {
        this.file = new File(filePath);
        if (!file.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write("0"); // Initialize account balance to 0
            }
        }
    }

    public void deposit(int amount) {
        lock.lock();
        try {
            int currentBalance = getBalance();
            int newBalance = currentBalance + amount;
            writeBalance(newBalance);
            System.out.println(Thread.currentThread().getName() + " deposited " + amount + ". New balance: " + newBalance);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void withdraw(int amount) {
        lock.lock();
        try {
            int currentBalance = getBalance();
            if (currentBalance >= amount) {
                int newBalance = currentBalance - amount;
                writeBalance(newBalance);
                System.out.println(Thread.currentThread().getName() + " withdrew " + amount + ". New balance: " + newBalance);
            } else {
                System.out.println(Thread.currentThread().getName() + " tried to withdraw " + amount + " but insufficient funds. Current balance: " + currentBalance);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public int getBalance() throws IOException {
        synchronized (file) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                return Integer.parseInt(reader.readLine());
            }
        }
    }

    private void writeBalance(int balance) throws IOException {
        synchronized (file) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(String.valueOf(balance));
            }
        }
    }
}
