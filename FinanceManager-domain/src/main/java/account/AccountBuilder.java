package account;

import exceptions.InvalidIdException;

public class AccountBuilder {

    private long id;
    private double balance = 0.0;
    private String accountName;
    private Owner owner;

    public Account build() throws InvalidIdException {
        if(!(id > 1000000000L && id < 9999999999L)){
            throw new InvalidIdException();
        }
        return new Account(accountName, owner, balance, id);
    }

    public AccountBuilder accountName(String accountName){
        this.accountName = accountName;
        return this;
    }

    public AccountBuilder owner(Owner owner){
        this.owner = owner;
        return this;
    }

    public AccountBuilder balance(double balance){
        this.balance = balance;
        return this;
    }

    public AccountBuilder id(long id){
        this.id = id;
        return this;
    }


}
