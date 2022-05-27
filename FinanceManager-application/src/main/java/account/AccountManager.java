package account;


import exceptions.AccountAlreadyExistsException;
import exceptions.AccountNotFoundException;
import transaction.Transaction;


public class AccountManager {

    private final AccountRepository accountRepository;


    public AccountManager(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account loadAccountByName(String accountName) throws AccountNotFoundException {
        return accountRepository.getAccountByName(accountName);
    }

    public Account loadAccountById(long id) throws AccountNotFoundException {
        return accountRepository.getAccountById(id);
    }

    public void createNewAccount(Account account) throws AccountAlreadyExistsException {
        accountRepository.add(account);
    }

    public void updateAccount(Account accountOld, Account accountNew) throws AccountAlreadyExistsException {
        this.accountRepository.update(accountOld, accountNew);
    }

    public void executeTransaction(Account account, Transaction transaction){
        if (transaction.getType().isDebit()){
            account.decreaseBalance(transaction.getAmount());
        } else {
            account.increaseBalance(transaction.getAmount());
        }
    }


}
