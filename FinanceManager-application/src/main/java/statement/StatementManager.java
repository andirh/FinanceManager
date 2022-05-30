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

    public MonthlyStatement getMonthlyStatement(int year, int month, Account account) throws InvalidStatementException, NoStatementFoundException, NoMonthlyStatementsFoundException {
        List<MonthlyStatement> monthlyStatements = statementRepository.getMonthlyStatements(account.getId());
        for (MonthlyStatement monthlyStatement : monthlyStatements) {
            if (monthlyStatement.getYear().getValue() == year) {
                if (monthlyStatement.getMonth().getValue() == month) {
                    return monthlyStatement;
                }
            }
        }
        throw new NoStatementFoundException();
    }

    public YearlyStatement getYearlyStatement(int year, Account account) throws InvalidStatementException, NoStatementFoundException, NoYearlyStatementsFoundException {
        List<YearlyStatement> yearlyStatements = statementRepository.getYearlyStatements(account.getId());
        for (YearlyStatement yearlyStatement : yearlyStatements) {
            if (yearlyStatement.getYear().getValue() == year) {
                return yearlyStatement;
            }
        }
        throw new NoStatementFoundException();
    }

    public OverallStatement getOverallStatement(Account account) throws InvalidStatementException, NoStatementFoundException, NoOverallStatementFoundException {
        return statementRepository.getOverallStatement(account.getId());
    }


    public void addTransactionToStatements(Account account, Transaction transaction) throws InvalidStatementException, StatementAlreadyExistsException {
        String[] transactionDate = transaction.getDate().split("/");
        boolean monthlyHasBeenAdded = false;
        boolean yearlyHasBeenAdded = false;
        try {
            try {
                List<MonthlyStatement> monthlyStatements = statementRepository.getMonthlyStatements(account.getId());
                for (MonthlyStatement statement : monthlyStatements) {
                    if ((statement.getYear().getValue() == Integer.parseInt(transactionDate[0])) && statement.getMonth().getValue() == Integer.parseInt(transactionDate[1])) {
                        statement.addTransaction(transaction);
                        statementRepository.update(statement, account.getId());
                        monthlyHasBeenAdded = true;
                    }
                }
            } catch (NoMonthlyStatementsFoundException e) {
                List<Transaction> transactions = new ArrayList<>();
                transactions.add(transaction);
                createMonthlyStatement(account.getId(), Integer.parseInt(transactionDate[0]), Integer.parseInt(transactionDate[1]), transactions);
            }

            try {
                List<YearlyStatement> yearlyStatements = statementRepository.getYearlyStatements(account.getId());
                for (YearlyStatement statement : yearlyStatements) {
                    if (statement.getYear().getValue() == Integer.parseInt(transactionDate[0])) {
                        statement.addTransaction(transaction);
                        statementRepository.update(statement, account.getId());
                        yearlyHasBeenAdded = true;
                    }
                }
            } catch (NoYearlyStatementsFoundException e) {
                List<Transaction> transactions = new ArrayList<>();
                transactions.add(transaction);
                createYearlyStatement(account.getId(), Integer.parseInt(transactionDate[0]), transactions);
            }
            try {
                OverallStatement overallStatement = statementRepository.getOverallStatement(account.getId());
                overallStatement.addTransaction(transaction);
                statementRepository.update(overallStatement, account.getId());
            } catch (NoOverallStatementFoundException e) {
                List<Transaction> transactions = new ArrayList<>();
                transactions.add(transaction);
                createOverallStatement(account.getId(), transactions);
            }


            if (!monthlyHasBeenAdded) {
                List<Transaction> transactions = new ArrayList<>();
                transactions.add(transaction);
                createMonthlyStatement(account.getId(), Integer.parseInt(transactionDate[0]), Integer.parseInt(transactionDate[1]), transactions);
            }

            if (!yearlyHasBeenAdded) {
                List<Transaction> transactions = new ArrayList<>();
                transactions.add(transaction);
                createYearlyStatement(account.getId(), Integer.parseInt(transactionDate[0]), transactions);
            }

        } catch (NoStatementFoundException ex) {
            List<Transaction> transactions = new ArrayList<>();
            transactions.add(transaction);
            createOverallStatement(account.getId(), transactions);

            List<Transaction> monthlyTransactions = new ArrayList<>();
            monthlyTransactions.add(transaction);
            createMonthlyStatement(account.getId(), Integer.parseInt(transactionDate[0]), Integer.parseInt(transactionDate[1]), monthlyTransactions);

            List<Transaction> yearlyTransactions = new ArrayList<>();
            yearlyTransactions.add(transaction);
            createYearlyStatement(account.getId(), Integer.parseInt(transactionDate[0]), yearlyTransactions);
        }
    }

    public double getSumOfStatement(Statement statement) throws InvalidStatementException {
        double sum = 0.0;
        for (Transaction transaction : statement.getAllTransactions()) {
            if (transaction.getType().isDebit()) {
                sum = sum - transaction.getAmount();
            } else if (transaction.getType().isPayment()) {
                sum = sum + transaction.getAmount();
            }
        }
        return sum;
    }


}
