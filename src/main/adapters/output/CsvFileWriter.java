package main.adapters.output;

import main.domain.account.Account;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

public class CsvFileWriter implements FileWriter{
    @Override
    public void reportTransaction() {

    }

    @Override
    public void reportAccountDetails(Account account) {
        File accountDetails = new File("src/resources/account", account.getAccountName() + ".csv");
        try {
            BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(accountDetails));
            writer.write(account.getOwner().getFistName() + "," + account.getOwner().getLastName() + "," + account.getBalance());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void reportStatement() {

    }
}
