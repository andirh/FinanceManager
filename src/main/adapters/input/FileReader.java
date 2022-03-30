package main.adapters.input;

import main.domain.account.Account;
import main.domain.transaction.TransactionHistory;

import java.time.Month;

public interface FileReader {

    Account extractAccountDetails();

    TransactionHistory extractTransactionsOfMonth(Month month, int year);

}
