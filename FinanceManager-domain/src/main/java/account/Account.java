package account;


import java.util.Objects;

public class Account {

    private final long id;
    private double balance = 0.0;
    private final String accountName;
    private final Owner owner;

    public Account(String accountName, Owner owner, double balance, long id) {
        this.balance = balance;
        this.accountName = accountName;
        this.owner = owner;
        this.id = id;
    }


    public long getId() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
