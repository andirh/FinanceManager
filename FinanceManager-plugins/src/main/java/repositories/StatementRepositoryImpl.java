package repositories;

import exceptions.*;
import mapping.StatementDataMapper;
import persistency.statement.StatementFileManager;
import statement.*;

import java.util.ArrayList;
import java.util.List;


public class StatementRepositoryImpl implements StatementRepository {
    private final StatementDataMapper dataMapper = new StatementDataMapper();
    private final StatementFileManager statementFileManager = new StatementFileManager();

    @Override
    public void add(Statement statement) throws InvalidStatementException {
        statementFileManager.createStatementFile(dataMapper.mapStatementToCsvString(statement));
    }

    @Override
    public void remove(Statement statement) {
        statementFileManager.removeStatementFile(statement.getStatementId(), statement.getAccountId());
    }

    @Override
    public void update(Statement statement) throws InvalidStatementException, StatementAlreadyExistsException {
        remove(statement);
        add(statement);
    }

    @Override
    public List<Statement> list(long accountId) throws InvalidStatementException, NoStatementFoundException {
        try {
            return dataMapper.extractStatements(statementFileManager.getStatementDataFromFile(accountId));
        } catch (IllegalDateException | InvalidIdException | InvalidTransactionTypeException | InvalidAmountException | InvalidStatementException  e) {
            throw new InvalidStatementException();
        }
    }

    @Override
    public List<MonthlyStatement> getMonthlyStatements(long accountId) throws InvalidStatementException, NoStatementFoundException {
        List<MonthlyStatement> monthlyStatements = new ArrayList<>();
        List<Statement> statements = list(accountId);
        for (Statement statement: statements) {
            if(statement instanceof MonthlyStatement){
                monthlyStatements.add((MonthlyStatement) statement);
            }
        }
        return monthlyStatements;
    }

    @Override
    public List<YearlyStatement> getYearlyStatements(long accountId) throws InvalidStatementException, NoStatementFoundException {
        List<YearlyStatement> yearlyStatements = new ArrayList<>();
        List<Statement> statements = list(accountId);
        for (Statement statement: statements) {
            if(statement instanceof YearlyStatement){
                yearlyStatements.add((YearlyStatement) statement);
            }
        }
        return yearlyStatements;
    }

    @Override
    public OverallStatement getOverallStatement(long accountId) throws NoStatementFoundException, InvalidStatementException {
        List<Statement> statements = list(accountId);
        for (Statement statement: statements) {
            if(statement instanceof OverallStatement){
                return (OverallStatement) statement;
            }
        }
        throw new NoStatementFoundException();
    }



}
