package statement;

import account.Account;
import exceptions.*;
import transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

public class StatementManager {

    private StatementRepository statementRepository;
    private StatementBuilder statementBuilder;

    public StatementManager(StatementRepository statementRepository) {
        this.statementRepository = statementRepository;
        this.statementBuilder = new StatementBuilder();
    }

    public void createMonthlyStatement(long accountId, int year, int month, List<Transaction> transactions) throws InvalidStatementException {
        try {
            Statement statement = statementBuilder.transactions(transactions).accountId(accountId).year(year).month(month).build();
            this.statementRepository.add(statement);
        } catch (InvalidIdException | IllegalDateException | StatementAlreadyExistsException e) {
            throw new InvalidStatementException();
        }
    }

    public void createYearlyStatement(long accountId, int year, List<Transaction> transactions) throws InvalidStatementException {
        try {
            Statement statement = statementBuilder.transactions(transactions).accountId(accountId).year(year).build();
            this.statementRepository.add(statement);
        } catch (InvalidIdException | IllegalDateException | StatementAlreadyExistsException e) {
            throw new InvalidStatementException();
        }
    }

    public void createOverallStatement(long accountId, List<Transaction> transactions) throws InvalidStatementException {
        try {
            Statement statement = statementBuilder.transactions(transactions).accountId(accountId).build();
            this.statementRepository.add(statement);
        } catch (InvalidIdException | IllegalDateException | StatementAlreadyExistsException e) {
            throw new InvalidStatementException();
        }
    }


    public void addTransactionToStatements(Account account, Transaction transaction) throws InvalidStatementException, NoStatementFoundException {
        List<MonthlyStatement> monthlyStatements = statementRepository.getMonthlyStatements(account.getId());
        String[] transactionDate = transaction.getDate().split("/");
        boolean addedToMonthly = false;
        boolean addedToYearly = false;

        for (MonthlyStatement statement: monthlyStatements) {
            if((statement.getYear().getValue() == Integer.parseInt(transactionDate[0])) && statement.getMonth().getValue() == Integer.parseInt(transactionDate[1])){
                statement.addTransaction(transaction);
                addedToMonthly = true;
            }
        }
        List<YearlyStatement> yearlyStatements = statementRepository.getYearlyStatements(account.getId());
        for (YearlyStatement statement: yearlyStatements) {
            if(statement.getYear().getValue() == Integer.parseInt(transactionDate[0])){
                statement.addTransaction(transaction);
                addedToYearly = true;
            }
        }
        try {
            OverallStatement overallStatement = statementRepository.getOverallStatement(account.getId());
            overallStatement.addTransaction(transaction);
        } catch (NoStatementFoundException ex){
            List<Transaction> transactions = new ArrayList<>();
            transactions.add(transaction);
            createOverallStatement(account.getId(), transactions);
        }
        if(!addedToMonthly){
            List<Transaction> transactions = new ArrayList<>();
            transactions.add(transaction);
            createMonthlyStatement(account.getId(),Integer.parseInt(transactionDate[0]), Integer.parseInt(transactionDate[1]), transactions);
        }
        if(!addedToYearly){
            List<Transaction> transactions = new ArrayList<>();
            transactions.add(transaction);
            createYearlyStatement(account.getId(), Integer.parseInt(transactionDate[0]), transactions);
        }
    }

    public double getSumOfStatement(Statement statement) throws InvalidStatementException {
        double sum = 0.0;
        for (Transaction transaction: statement.getAllTransactions()) {
            if (transaction.getType().isDebit()){
                sum = sum - transaction.getAmount();
            } else if(transaction.getType().isPayment()){
                sum = sum +  transaction.getAmount();
            }
        }
        return sum;
    }


}
