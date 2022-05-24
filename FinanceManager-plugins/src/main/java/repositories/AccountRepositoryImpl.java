package repositories;

import account.Account;
import account.AccountRepository;
import exceptions.AccountNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryImpl implements AccountRepository {

    private final List<Account> accounts;

    public AccountRepositoryImpl() {
        accounts = new ArrayList<>();
    }

    public AccountRepositoryImpl(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public void add(Account account) {
        accounts.add(account);
    }

    @Override
    public void remove(Account account) {
        accounts.remove(account);
    }

    @Override
    public void update(Account account) {
        accounts.add(account);
        accounts.remove(account);
    }

    @Override
    public List<Account> list() {
        return accounts;
    }

    @Override
    public Account getAccountById(int id) throws AccountNotFoundException {
        for (Account account: accounts) {
            if (account.getId() == id){
                return account;
            }
        }
        throw new AccountNotFoundException();
    }
}
