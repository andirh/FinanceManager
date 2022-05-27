package account;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    private Account account;

    @BeforeEach
    public void init(){
        Owner owner = new Owner("Test", "Tersterich");
        account = new Account("Test Account", owner, 42, 1234567890);
    }

    @Test
    void increaseBalance() {
        account.increaseBalance(10);
        assertEquals(account.getBalance(), 52);
    }

    @Test
    void decreaseBalance() {
        account.decreaseBalance(10);
        assertEquals(account.getBalance(), 32);
    }

    @Test
    void decreaseBalanceUnderZero() {
        account.decreaseBalance(100);
        assertEquals(account.getBalance(), -58);
    }
}