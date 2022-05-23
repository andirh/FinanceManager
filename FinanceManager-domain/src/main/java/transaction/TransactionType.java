package transaction;

import java.util.Objects;

public class TransactionType {

    final boolean debit;
    final boolean payment;

    public TransactionType(boolean debit, boolean payment) {
        this.debit = debit;
        this.payment = payment;
        if(this.debit == this.payment){
            //throw new InvalidTransactionTypeException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionType that = (TransactionType) o;
        return debit == that.debit && payment == that.payment;
    }

    @Override
    public int hashCode() {
        return Objects.hash(debit, payment);
    }

    public boolean isDebit() {
        return debit;
    }

    public boolean isPayment() {
        return payment;
    }
}
