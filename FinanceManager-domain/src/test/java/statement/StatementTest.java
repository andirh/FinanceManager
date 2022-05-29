package statement;

import comparators.StatementComparator;
import exceptions.*;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import transaction.Transaction;
import transaction.TransactionType;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StatementTest {

    private Statement monthlyStatement;
    private Statement yearlyStatement;
    private Statement overallStatement;
    private List<Transaction> transactions;
    private Transaction transaction1;
    private Transaction transaction2;
    private Transaction transaction3;
    private Transaction transaction4;


    @BeforeEach
    public void init() throws InvalidIdException {
        transactions = new ArrayList<>();
        TransactionType debit = mock(TransactionType.class);
        when(debit.isDebit()).thenReturn(true);
        TransactionType payment = mock(TransactionType.class);
        when(payment.isPayment()).thenReturn(true);
        transaction1 = mockTransaction("Wocheneinkauf", 60.0, "2022/05/27", debit);
        transaction2 = mockTransaction("Weinfest Besuch", 200.0, "2022/05/26", payment);
        transaction3 = mockTransaction("Gehalt", 5.0, "2022/05/25", payment);
        transaction4 = mockTransaction("Rückerstattung", 80.0, "2022/05/29", debit);
        transactions.add(transaction1);
        transactions.add(transaction2);
        transactions.add(transaction3);
        transactions.add(transaction4);
        monthlyStatement = new MonthlyStatement(2022, 5, transactions, 1234567890L);
        yearlyStatement = new YearlyStatement(2022, transactions, 1234567890L);
        overallStatement = new OverallStatement(transactions, 1234567890L);
    }

    private Transaction mockTransaction(String category, double amount, String date, TransactionType type) {
        Transaction transaction = mock(Transaction.class);
        when(transaction.getCategory()).thenReturn(category);
        when(transaction.getAmount()).thenReturn(amount);
        when(transaction.getDate()).thenReturn(date);
        when(transaction.getType()).thenReturn(type);
        return transaction;
    }

    @Test
    void getAllPayments() throws NoPaymentsException, InvalidStatementException {
        Transaction[] payments = monthlyStatement.getAllPayments().toArray(new Transaction[0]);
        assertEquals(payments[0], transaction3);
        assertEquals(payments[1], transaction2);
    }

    @Test
    void getAllPaymentsThrowsErrorIfNoTransactionsAvailable() throws InvalidIdException, NoPaymentsException {
        try {
            List<Transaction> transactions = new ArrayList<>();
            MonthlyStatement monthlyStatement = new MonthlyStatement(2022, 5, transactions, 1234567890L);
            Transaction[] payments = monthlyStatement.getAllPayments().toArray(new Transaction[0]);
            fail("InvalidStatementException did not occur");
        } catch (InvalidStatementException e) {
            assertEquals(e.getMessage(), "Invalid Statement");
        }
    }

    @Test
    void getAllPaymentsThrowsErrorIfNoPaymentsAvailable() throws InvalidIdException,  InvalidStatementException {
        //Nach der Exception testen
        try {
            List<Transaction> transactions = new ArrayList<>();
            TransactionType debit = mock(TransactionType.class);
            when(debit.isDebit()).thenReturn(true);
            transactions.add(mockTransaction("Weinfest Besuch", 200.0, "2022/05/26", debit));
            MonthlyStatement monthlyStatement = new MonthlyStatement(2022, 5, transactions, 1234567890L);
            Transaction[] payments = monthlyStatement.getAllPayments().toArray(new Transaction[0]);
            fail("NoPaymentsException did not occur");
        } catch (NoPaymentsException e) {
            assertEquals(e.getMessage(), "No payments could be found");
        }
    }

    @Test
    void getAllDebits() throws InvalidStatementException, NoDebitsException {
        Transaction[] debits = monthlyStatement.getAllDebits().toArray(new Transaction[0]);
        assertEquals(debits[0], transaction1);
        assertEquals(debits[1], transaction4);
    }

    @Test
    void getStatementIdFromMonthlyStatement() {
        assertEquals(monthlyStatement.getStatementId(), "2022-5-1234567890");
    }

    @Test
    void getStatementIdFromYearlyStatement() {
        assertEquals(yearlyStatement.getStatementId(), "2022-1234567890");
    }

    @Test
    void getStatementIdFromOverallStatement() {
        assertEquals(overallStatement.getStatementId(), "O-1234567890");
    }
}