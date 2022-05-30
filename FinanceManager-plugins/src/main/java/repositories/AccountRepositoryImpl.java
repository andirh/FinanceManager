package repositories;

import account.Account;
import account.AccountRepository;
import exceptions.*;
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
        accountFileManager.updateBalance(dataMapper.mapAccountToCsvString(account));
    }

    @Override
    public List<Account> list() throws InvalidAccountException, InvalidIdException, NoAccountFilesException {
        return dataMapper.extractAccounts(accountFileManager.getAccountDataFromFile());
    }

    @Override
    public Account getAccountById(long id) throws AccountNotFoundException, NoAccountFilesException {
        try {
            List<Account> accounts = dataMapper.extractAccounts(accountFileManager.getAccountDataFromFile());
            for (Account account : accounts) {
                if (account.getId() == id) {
                    return account;
                }
            }
        } catch (InvalidIdException | InvalidAccountException e) {
            throw new AccountNotFoundException();
        }
        throw new AccountNotFoundException();
    }

    @Override
    public Account getAccountByName(String accountName) throws AccountNotFoundException, NoAccountFilesException {
        try {
            List<Account> accounts = dataMapper.extractAccounts(accountFileManager.getAccountDataFromFile());
            for (Account account : accounts) {
                if (account.getAccountName().equals(accountName)) {
                    return account;
                }
            }
        } catch (InvalidIdException | InvalidAccountException e) {
            throw new AccountNotFoundException();
        }
        throw new AccountNotFoundException();
    }

}
