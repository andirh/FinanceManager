package mapping;

import account.Account;
import exceptions.*;
import statement.Statement;
import statement.StatementBuilder;
import transaction.Transaction;
import transaction.TransactionBuilder;
import transaction.TransactionType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StatementDataMapper {


    public List<Statement> extractStatements(List<String[]> statementData) throws IllegalDateException, InvalidIdException, InvalidTransactionTypeException, InvalidAmountException {
        List<Statement> statements = new ArrayList<>();
        StatementBuilder statementBuilder = new StatementBuilder();
        TransactionBuilder transactionBuilder = new TransactionBuilder();
        for (String[] statementLinesArray : statementData) {
            List<String> statementLines = Arrays.asList(statementLinesArray);
            String[] statementMetaData = statementLines.get(0).split(",");
            statementLines.remove(0);
            List<Transaction> transactions = new ArrayList<>();
            for (String transactionString: statementLines) {
                String[] transactionInformation = transactionString.split(",");
                Transaction transaction = transactionBuilder.date(transactionInformation[0]).amount(Double.parseDouble(transactionInformation[1]))
                        .category(transactionInformation[2])
                        .type(transactionInformation[3].equals("debit") ? new TransactionType(true, false)
                                : new TransactionType(false, true) ).build();
                transactions.add(transaction);
            }
            if(statementMetaData.length == 5){
                statements.add(statementBuilder.accountId(Long.parseLong(statementMetaData[0])).year(Integer.parseInt(statementMetaData[2])).month(Integer.parseInt(statementMetaData[3])).transactions(transactions).build());
            } else if(statementMetaData.length == 4){
                statements.add(statementBuilder.accountId(Long.parseLong(statementMetaData[0])).year(Integer.parseInt(statementMetaData[2])).transactions(transactions).build());
            } else {
                statements.add(statementBuilder.accountId(Long.parseLong(statementMetaData[0])).transactions(transactions).build());
            }
        }
        return statements;
    }

    public String mapStatementToCsvString(Statement statement) throws InvalidStatementException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(statement.toString());
        for (Transaction transaction: statement.getAllTransactions()) {
            stringBuilder.append(transaction.getDate()).append(",").append(transaction.getAmount()).append(",").append(transaction.getCategory()).append(",").append(transaction.getType().toString());
        }
        return stringBuilder.toString();
    }




}
