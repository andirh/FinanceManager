package main.adapters.input;

import main.domain.account.Account;
import main.domain.account.Owner;
import main.domain.transaction.TransactionHistory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.time.Month;

public class CsvFileReader implements FileReader{


    @Override
    public Account extractAccountDetails(File accountDetails) {
        Account account = null;
        try {
            BufferedReader reader = new BufferedReader(new java.io.FileReader(accountDetails));
            String[] extractedAccountDetails = reader.readLine().split(",");
            Owner owner = new Owner(extractedAccountDetails[0], extractedAccountDetails[1]);
            account = new Account(accountDetails.getName().replace(".csv", ""), owner, Double.parseDouble(extractedAccountDetails[2]));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public TransactionHistory extractTransactionsOfMonth(Month month, int year) {
        return null;
    }
}
