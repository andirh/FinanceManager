package application;

import account.AccountManager;
import account.AccountRepository;
import statement.StatementManager;
import statement.StatementRepository;
import transaction.TransactionManager;

public class ApplicationManager {

    private final StatementRepository statementRepository;
    private final AccountRepository accountRepository;
    private AccountManager accountManager;
    private StatementManager statementManager;
    private TransactionManager transactionManager;

    public ApplicationManager(StatementRepository statementRepository, AccountRepository accountRepository) {
        this.statementRepository = statementRepository;
        this.accountRepository = accountRepository;
    }

    public void init(){
        accountManager = new AccountManager(accountRepository);
        statementManager = new StatementManager(statementRepository);
        transactionManager = new TransactionManager(accountManager, statementManager);
    }

    public AccountManager getAccountManager() {
        return accountManager;
    }

    public StatementManager getStatementManager() {
        return statementManager;
    }

    public TransactionManager getTransactionManager() {
        return transactionManager;
    }
}
