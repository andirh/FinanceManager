package main.adapters.input;

import main.domain.account.Account;
import main.domain.transaction.TransactionHistory;

import java.io.File;
import java.time.Month;

public interface FileReader {

    Account extractAccountDetails(File accountDetails);

    TransactionHistory extractTransactionsOfMonth(Month month, int year);

}
