package main;

import main.model.Account;
import main.model.Owner;

public class FinanceManager {

    public static void main(String args[]){

        Owner owner = new Owner("Andreas", "Rheinwalt");

        Account account = new Account(12345678, owner);

        account.increaseBalance(50);
        System.out.println(account.getBalance());
        account.decreaseBalance(10);
        System.out.println(account.getBalance());
    }


}
