package account;

import exceptions.AccountAlreadyExistsException;
import exceptions.AccountNotFoundException;
import exceptions.InvalidAccountException;
import exceptions.InvalidIdException;

import java.util.List;


public interface AccountRepository {
    void add(Account account) throws AccountAlreadyExistsException;

    void remove(Account account);

    void update(Account accountOld, Account accountNew) throws AccountAlreadyExistsException;

    List<Account> list() throws InvalidAccountException, InvalidIdException;

    Account getAccountById(long id) throws AccountNotFoundException;

    Account getAccountByName(String accountName) throws  AccountNotFoundException;

}
