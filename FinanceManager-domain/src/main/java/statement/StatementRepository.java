package statement;

import account.Account;
import exceptions.*;

import java.util.List;


public interface StatementRepository {
    void add(Statement statement) throws InvalidStatementException, StatementAlreadyExistsException;

    void remove(Statement statement);

    void update(Statement statement, long accountId) throws InvalidStatementException, StatementAlreadyExistsException, NoStatementFoundException;

    List<Statement> list(long accountId) throws InvalidStatementException, NoStatementFoundException;

    List<MonthlyStatement> getMonthlyStatements(long accountId) throws InvalidStatementException, NoStatementFoundException, NoMonthlyStatementsFoundException;

    List<YearlyStatement> getYearlyStatements(long accountId) throws NoStatementFoundException, InvalidStatementException, NoYearlyStatementsFoundException;

    OverallStatement getOverallStatement(long accountId) throws NoStatementFoundException, InvalidStatementException, NoOverallStatementFoundException;
}
