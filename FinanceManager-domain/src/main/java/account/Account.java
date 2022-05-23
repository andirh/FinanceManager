package account;

import java.util.Random;

public class Account {

    private int id;
    private double balance = 0;
    private String accountName;
    private Owner owner;

    public Account(String accountName, Owner owner) {
        this.accountName = accountName;
        this.owner = owner;
        Random rnd = new Random();
        this.id = 10000000 + rnd.nextInt(90000000);
    }

    public Account(String accountName, Owner owner, double balance, int id) {
        this.balance = balance;
        this.accountName = accountName;
        this.owner = owner;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountName() {
        return accountName;
    }

    public Owner getOwner() {
        return owner;
    }

    public void increaseBalance(double amount){
        balance += amount;
    }

    public void decreaseBalance(double amount){
        balance -= amount;
    }

}
