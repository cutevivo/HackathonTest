import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

public class Transaction {
    public final double amount;
    public static DecimalFormat df = new DecimalFormat("#,###.00");
    private enum Type {
        DEPOSIT, WITHDRAWAL;

        public String toString() {
            return name().toLowerCase();
        }
    }

    private Date transactionDate;
    private Type type;

    public Transaction(double amount) {
        this.type = amount > 0 ? Type.DEPOSIT : Type.WITHDRAWAL;
        this.amount = Math.abs(amount);
        this.transactionDate = DateProvider.getInstance().now();
    }

    public double getAmount() {
        double amount = 0.0;
        switch (this.type) {
            case DEPOSIT:
                amount = this.amount;
                break;
            case WITHDRAWAL:
                amount = -this.amount;
                break;
        }
        return amount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(type);
        sb.append(" $");
        sb.append(df.format(amount));
        return sb.toString();
    }
}
