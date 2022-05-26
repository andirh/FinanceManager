package account;


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

}
