package account;

import exceptions.AccountAlreadyExistsException;
import exceptions.AccountNotFoundException;
import exceptions.InvalidAccountException;

import java.util.List;


public interface AccountRepository {
    void add(Account account) throws AccountAlreadyExistsException;

    void remove(Account account);

    void update(Account account) throws AccountAlreadyExistsException;

    List<Account> list() throws InvalidAccountException;

    Account getAccountById(int id) throws AccountNotFoundException, InvalidAccountException;

    Account getAccountByName(String accountName) throws InvalidAccountException, AccountNotFoundException;
}
