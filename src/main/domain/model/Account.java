package main.model;

public class Account {

    private double balance = 0;

    private final long accountNumber;
    private Owner owner;

    public Account(long accountNumber, Owner owner) {
        this.accountNumber = accountNumber;
        this.owner = owner;
    }

    public Account(double balance, long accountNumber, Owner owner) {
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.owner = owner;
    }

    public double getBalance() {
        return balance;
    }

    public long getAccountNumber() {
        return accountNumber;
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
