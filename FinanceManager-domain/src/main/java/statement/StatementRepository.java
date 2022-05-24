package statement;

import account.Account;
import exceptions.NoStatementFoundException;

import java.util.List;


public interface StatementRepository {
    void add(Statement statement);

    void remove(Statement statement);

    void update(Statement statement);

    List<Statement> list();

    List<Statement> getMonthlyStatements() throws NoStatementFoundException;

    List<Statement> getYearlyStatements() throws NoStatementFoundException;

    Statement getOverallStatement() throws NoStatementFoundException;
}
