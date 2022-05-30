package transaction;

import exceptions.InvalidAmountException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TransactionTest {

    @Test
    void checkIfAmountIsValid() {
        TransactionType debit = mock(TransactionType.class);
        when(debit.isDebit()).thenReturn(true);
        try {
            new Transaction("Einkauf", 0.0, "2022/05/23", debit);
            fail("InvalidAmountException did not occur");
        } catch (InvalidAmountException e) {
            assertEquals(e.getMessage(), "Invalid amount");
        }
    }

    @Test
    void checkIfCategoryIsAdded() throws InvalidAmountException {
        TransactionType debit = mock(TransactionType.class);
        when(debit.isDebit()).thenReturn(true);
        Transaction transaction = new Transaction(null, 1.0, "2022/05/23", debit);
        assertEquals(transaction.getCategory(), "---");

    }
}