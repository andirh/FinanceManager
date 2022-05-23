package output;

import main.domain.account.Account;

public interface FileWriter {

    void reportTransaction();

    void reportAccountDetails(Account account);

    void reportStatement();

}
