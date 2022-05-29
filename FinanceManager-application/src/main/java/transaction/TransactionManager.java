package transaction;

import account.Account;
import account.AccountManager;
import exceptions.InvalidAmountException;
import exceptions.InvalidStatementException;
import exceptions.NoStatementFoundException;
import statement.StatementManager;

public class TransactionManager {

    private TransactionBuilder builder = new TransactionBuilder();
    private AccountManager accountManager;
    private StatementManager statementManager;

    public TransactionManager(AccountManager accountManager, StatementManager statementManager) {
        this.accountManager = accountManager;
        this.statementManager = statementManager;
    }

    public Transaction createTransaction(String category, double amount, String date, TransactionType type) throws InvalidAmountException {
        return builder.date(date).category(category).amount(amount).type(type).build();
    }

    public Transaction createTransaction(String category, double amount, TransactionType type) throws InvalidAmountException {
        return builder.category(category).amount(amount).type(type).build();
    }

    public void executeTransaction(Transaction transaction, Account account) throws InvalidStatementException, NoStatementFoundException {
        accountManager.executeTransactionOnAccount(account,transaction);
        statementManager.addTransactionToStatements(account, transaction);
    }

}
