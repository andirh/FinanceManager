package repositories;

import exceptions.NoStatementFoundException;
import statement.*;

import java.util.ArrayList;
import java.util.List;

public class StatementRepositoryImpl implements StatementRepository {

    private final List<Statement> statements;

    public StatementRepositoryImpl() {
        this.statements = new ArrayList<>();
    }
    public StatementRepositoryImpl(List<Statement> statements) {
        this.statements = statements;
    }

    @Override
    public void add(Statement statement) {
        statements.add(statement);
    }

    @Override
    public void remove(Statement statement) {
        statements.remove(statement);
    }

    @Override
    public void update(Statement statement) {
        statements.remove(statement);
        statements.add(statement);
    }

    @Override
    public List<Statement> list() {
        return statements;
    }

    @Override
    public List<Statement> getMonthlyStatements() throws NoStatementFoundException {
        if (statements.size() == 0){
            throw new NoStatementFoundException();
        }
        List<Statement> monthlyStatements = new ArrayList<>();
        for (Statement statement: statements) {
            if(statement instanceof MonthlyStatement){
                monthlyStatements.add(statement);
            }
        }
        return monthlyStatements;
    }

    @Override
    public List<Statement> getYearlyStatements() throws NoStatementFoundException {
        if (statements.size() == 0){
            throw new NoStatementFoundException();
        }
        List<Statement> yearlyStatements = new ArrayList<>();
        for (Statement statement: statements) {
            if(statement instanceof YearlyStatement){
                yearlyStatements.add(statement);
            }
        }
        return yearlyStatements;
    }

    @Override
    public Statement getOverallStatement() throws NoStatementFoundException {
        for (Statement statement: statements) {
            if(statement instanceof OverallStatement){
                return statement;
            }
        }
        throw new NoStatementFoundException();
    }

}
