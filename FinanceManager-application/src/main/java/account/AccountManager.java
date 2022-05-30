package account;


import exceptions.AccountAlreadyExistsException;
import exceptions.AccountNotFoundException;
import exceptions.NoAccountFilesException;
import transaction.Transaction;


public class AccountManager {

    private final AccountRepository accountRepository;


    public AccountManager(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account loadAccountByName(String accountName) throws AccountNotFoundException, NoAccountFilesException {
        return accountRepository.getAccountByName(accountName);
    }

    public Account loadAccountById(long id) throws AccountNotFoundException, NoAccountFilesException {
        return accountRepository.getAccountById(id);
    }

    public void createNewAccount(Account account) throws AccountAlreadyExistsException {
        accountRepository.add(account);
    }

    public void updateAccount(Account account) throws AccountAlreadyExistsException {
        this.accountRepository.update(account);
    }

    public void executeTransactionOnAccount(Account account, Transaction transaction) throws AccountAlreadyExistsException {
        if (transaction.getType().isDebit()){
            account.decreaseBalance(transaction.getAmount());
        } else {
            account.increaseBalance(transaction.getAmount());
        }
        accountRepository.update(account);
    }

    public Account loadAccount(String accountName, String accountId) throws NoAccountFilesException, AccountNotFoundException {
        if(accountName!=null){
            return loadAccountByName(accountName);
        }
        if (accountId!=null){
            long accountLong = Long.parseLong(accountId);
            return loadAccountById(accountLong);
        }
        return null;
    }


}
