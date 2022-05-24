package statement;

import account.Account;

import java.util.List;


public interface StatementRepository {
    void add(Account account);

    void remove(Account account);

    void update(Account account);

    List<Statement> list();
}
