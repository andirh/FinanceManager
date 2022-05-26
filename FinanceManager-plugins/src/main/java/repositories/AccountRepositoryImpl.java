package repositories;

import account.Account;
import account.AccountRepository;
import exceptions.AccountAlreadyExistsException;
import exceptions.AccountNotFoundException;
import exceptions.InvalidAccountException;
import mapping.AccountDataMapper;
import persistency.account.AccountFileManager;

import java.util.List;

public class AccountRepositoryImpl implements AccountRepository {

    private final AccountDataMapper dataMapper = new AccountDataMapper();
    private final AccountFileManager accountFileManager = new AccountFileManager();

    @Override
    public void add(Account account) throws AccountAlreadyExistsException {
        accountFileManager.createAccountFile(dataMapper.mapAccountToCsvString(account));
    }

    @Override
    public void remove(Account account) {
        accountFileManager.removeAccountFile(dataMapper.extractAccountNameFromAccount(account));
    }

    @Override
    public void update(Account account) throws AccountAlreadyExistsException {
        remove(account);
        add(account);
    }

    @Override
    public List<Account> list() throws InvalidAccountException {
        return dataMapper.extractAccounts(accountFileManager.getAccountDataFromFile());
    }

    @Override
    public Account getAccountById(int id) throws InvalidAccountException, AccountNotFoundException {
        List<Account> accounts = dataMapper.extractAccounts(accountFileManager.getAccountDataFromFile());
        for (Account account : accounts) {
            if(account.getId() == id){
                return account;
            }
        }
        throw new AccountNotFoundException();
    }

    @Override
    public Account getAccountByName(String accountName) throws InvalidAccountException, AccountNotFoundException {
        List<Account> accounts = dataMapper.extractAccounts(accountFileManager.getAccountDataFromFile());
        for (Account account : accounts) {
            if(account.getAccountName().equals(accountName)){
                return account;
            }
        }
        throw new AccountNotFoundException();
    }
}
