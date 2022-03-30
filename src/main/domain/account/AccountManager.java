package main.domain.account;

import main.adapters.input.CsvFileReader;
import main.adapters.input.FileReader;
import main.adapters.output.CsvFileWriter;
import main.adapters.output.FileWriter;

import java.io.File;

public class AccountManager {

    private final FileWriter fileWriter;
    private final FileReader fileReader;

    public AccountManager() {
        fileWriter = new CsvFileWriter();
        fileReader = new CsvFileReader();
    }

    public Account createAccount(String accountName, Owner owner, double balance){
        Account account = new Account(accountName, owner, balance);
        fileWriter.reportAccountDetails(account);
        return account;
    }

    public Account createAccount(String accountName, Owner owner){
        Account account = new Account(accountName, owner);
        fileWriter.reportAccountDetails(account);
        return account;
    }

    public Account loadExistingAccount(File accountDetails){
        return fileReader.extractAccountDetails(accountDetails);
    }

}
