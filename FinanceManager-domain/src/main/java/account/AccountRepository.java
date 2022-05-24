package account;

import exceptions.AccountNotFoundException;

import java.util.List;


public interface AccountRepository {
    void add(Account account);

    void remove(Account account);

    void update(Account account);

    List<Account> list();

    Account getAccountById(int id) throws AccountNotFoundException;
}
