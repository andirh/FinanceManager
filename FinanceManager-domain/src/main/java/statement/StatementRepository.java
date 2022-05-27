package statement;

import account.Account;
import exceptions.*;

import java.util.List;


public interface StatementRepository {
    void add(Statement statement) throws InvalidStatementException, StatementAlreadyExistsException;

    void remove(Statement statement);

    void update(Statement statement) throws InvalidStatementException, StatementAlreadyExistsException;

    List<Statement> list(long accountId) throws InvalidStatementException;

    List<MonthlyStatement> getMonthlyStatements(long accountId) throws  InvalidStatementException;

    List<YearlyStatement> getYearlyStatements(long accountId) throws NoStatementFoundException, InvalidStatementException;

    OverallStatement getOverallStatement(long accountId) throws NoStatementFoundException, InvalidStatementException;
}
