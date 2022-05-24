package input;
/*
import main.domain.account.Account;
import main.domain.account.Owner;
import main.domain.transaction.TransactionHistory;

import javax.security.auth.login.AccountException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.time.Month;

public class CsvFileReader implements FileReader{


    @Override
    public Account extractAccountDetails(File accountDetails) throws AccountException {
        Account account = null;
        try {
            BufferedReader reader = new BufferedReader(new java.io.FileReader(accountDetails));
            String[] extractedAccountDetails = reader.readLine().split(",");
            if (extractedAccountDetails.length < 4){
                throw new AccountException();
            }
            Owner owner = new Owner(extractedAccountDetails[0], extractedAccountDetails[1]);
            account = new Account(accountDetails.getName().replace(".csv", ""), owner, Double.parseDouble(extractedAccountDetails[2]), Integer.parseInt(extractedAccountDetails[3]));
        } catch (IOException | NullPointerException e) {
            throw new AccountException();
        }
        return account;
    }

    @Override
    public TransactionHistory extractTransactionsOfMonth(Month month, int year) {
        return null;
    }
}


 */