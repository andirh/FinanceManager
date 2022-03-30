package main.domain.account;

public class Account {

    private double balance = 0;
    private String accountName;
    private Owner owner;

    public Account(String accountName, Owner owner) {
        this.accountName = accountName;
        this.owner = owner;
    }

    public Account(String accountName, Owner owner, double balance) {
        this.balance = balance;
        this.accountName = accountName;
        this.owner = owner;
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
