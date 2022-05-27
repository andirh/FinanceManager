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
    public void add(Statement statement) throws InvalidStatementException, StatementAlreadyExistsException {
        statementFileManager.createStatementFile(dataMapper.mapStatementToCsvString(statement));
    }

    @Override
    public void remove(Statement statement) {
        statementFileManager.removeStatementFile(statement.getStatementId());
    }

    @Override
    public void update(Statement statement) throws InvalidStatementException, StatementAlreadyExistsException {
        remove(statement);
        add(statement);
    }

    @Override
    public List<Statement> list() throws InvalidStatementException {
        try {
            return dataMapper.extractStatements(statementFileManager.getStatementDataFromFile());
        } catch (IllegalDateException | InvalidIdException | InvalidTransactionTypeException | InvalidAmountException | InvalidStatementException e) {
            throw new InvalidStatementException();
        }
    }

    @Override
    public List<Statement> getMonthlyStatements() throws  InvalidStatementException{
        List<Statement> monthlyStatements = new ArrayList<>();
        List<Statement> statements = list();
        for (Statement statement: statements) {
            if(statement instanceof MonthlyStatement){
                monthlyStatements.add(statement);
            }
        }
        return monthlyStatements;
    }

    @Override
    public List<Statement> getYearlyStatements() throws InvalidStatementException {
        List<Statement> yearlyStatements = new ArrayList<>();
        List<Statement> statements = list();
        for (Statement statement: statements) {
            if(statement instanceof MonthlyStatement){
                yearlyStatements.add(statement);
            }
        }
        return yearlyStatements;
    }

    @Override
    public Statement getOverallStatement() throws NoStatementFoundException, InvalidStatementException {
        List<Statement> statements = list();
        for (Statement statement: statements) {
            if(statement instanceof OverallStatement){
                return statement;
            }
        }
        throw new NoStatementFoundException();
    }



}
