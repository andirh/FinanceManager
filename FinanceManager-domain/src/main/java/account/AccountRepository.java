package account;

import exceptions.*;

import java.util.List;


public interface AccountRepository {
    void add(Account account) throws AccountAlreadyExistsException;

    void remove(Account account);

    void update(Account account) throws AccountAlreadyExistsException;

    List<Account> list() throws InvalidAccountException, InvalidIdException, NoAccountFilesException;

    Account getAccountById(long id) throws AccountNotFoundException, NoAccountFilesException;

    Account getAccountByName(String accountName) throws AccountNotFoundException, NoAccountFilesException;

}
