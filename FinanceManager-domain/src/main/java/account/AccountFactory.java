package account;

import java.util.Random;

public class AccountFactory {

    private int id = 0;
    private double balance = 0.0;
    private String accountName;
    private Owner owner;

    public Account build() {
        if(this.id < 10000000){
            Random rnd = new Random();
            this.id = 10000000 + rnd.nextInt(90000000);
        }
        return new Account(accountName, owner, balance, id);
    }

    public AccountFactory accountName(String accountName){
        this.accountName = accountName;
        return this;
    }

    public AccountFactory owner(Owner owner){
        this.owner = owner;
        return this;
    }

    public AccountFactory balance(double balance){
        this.balance = balance;
        return this;
    }

    public AccountFactory id(int id){
        this.id = id;
        return this;
    }


}
