import java.util.Calendar;
import java.util.Date;

public class Transaction {
    public final double amount;

    private enum Type {
        DEPOSIT, WITHDRAW;

        public String toString() {
            return name().toLowerCase();
        }
    }

    private Date transactionDate;
    private Type type;

    public Transaction(double amount) {
        this.type = amount > 0 ? Type.DEPOSIT : Type.WITHDRAW;
        this.amount = Math.abs(amount);
        this.transactionDate = DateProvider.getInstance().now();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(type);
        sb.append(" $");
        sb.append(amount);
        return sb.toString();
    }
}
