package statement;

import account.Account;
import exceptions.*;

import java.util.List;


public interface StatementRepository {
    void add(Statement statement) throws InvalidStatementException, StatementAlreadyExistsException;

    void remove(Statement statement);

    void update(Statement statement) throws InvalidStatementException, StatementAlreadyExistsException;

    List<Statement> list() throws InvalidStatementException;

    List<Statement> getMonthlyStatements() throws  InvalidStatementException;

    List<Statement> getYearlyStatements() throws NoStatementFoundException, InvalidStatementException;

    Statement getOverallStatement() throws NoStatementFoundException, InvalidStatementException;
}
