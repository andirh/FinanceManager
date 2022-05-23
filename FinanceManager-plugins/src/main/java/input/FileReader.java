package input;

import main.domain.account.Account;
import main.domain.transaction.TransactionHistory;

import javax.security.auth.login.AccountException;
import java.io.File;
import java.time.Month;

public interface FileReader {

    Account extractAccountDetails(File accountDetails) throws AccountException;

    TransactionHistory extractTransactionsOfMonth(Month month, int year);

}
