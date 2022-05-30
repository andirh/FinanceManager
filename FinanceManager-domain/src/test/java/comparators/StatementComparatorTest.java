package comparators;

import exceptions.InvalidIdException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import statement.MonthlyStatement;
import statement.Statement;
import transaction.Transaction;
import transaction.TransactionType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StatementComparatorTest {

    private List<Transaction> transactions;
    private Transaction transaction1;
    private Transaction transaction2;
    private Transaction transaction3;
    private Transaction transaction4;


    @BeforeEach
    public void init() {
        transactions = new ArrayList<>();
        TransactionType debit = mock(TransactionType.class);
        when(debit.isDebit()).thenReturn(true);
        TransactionType payment = mock(TransactionType.class);
        when(payment.isPayment()).thenReturn(true);
        transaction1 = mockTransaction("Wocheneinkauf", 60.0, "2022/05/27", debit);
        transaction2 = mockTransaction("Weinfest Besuch", 200.0, "2022/05/26", debit);
        transaction3 = mockTransaction("Gehalt", 5.0, "2022/05/25", payment);
        transaction4 = mockTransaction("Rückerstattung", 80.0, "2022/05/29", payment);
        transactions.add(transaction1);
        transactions.add(transaction2);
        transactions.add(transaction3);
        transactions.add(transaction4);
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
    void compare() {
        transactions.sort(new StatementComparator());
        assertEquals(transactions.get(0), transaction3);
        assertEquals(transactions.get(1), transaction2);
        assertEquals(transactions.get(2), transaction1);
        assertEquals(transactions.get(3), transaction4);
    }
}