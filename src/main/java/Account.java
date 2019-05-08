import java.util.ArrayList;
import java.util.List;
import java.lang.IllegalArgumentException;

public class Account {

    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;

    private final int accountType;
    public List<Transaction> transactions;

    public Account(int accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
    }

    //存款,需对存款数<=0的情况进行异常处理,异常详情参考测试用例
    public void deposit(double amount) throws IllegalArgumentException{
        if(amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        }
        Transaction transaction = new Transaction(amount);
        this.transactions.add(transaction);
    }

    //取款,需对取款数和账户余额<=0的情况分别进行异常处理,异常详情参考测试用例
    public void withdraw(double amount) throws IllegalArgumentException{
        if(amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        }
        Transaction transaction = new Transaction(-amount);
        this.transactions.add(transaction);
        if(sumTransactions() < 0) {
            this.transactions.remove(this.transactions.size() - 1);
            throw new IllegalArgumentException("sumTransactions must be greater than zero");
        }
    }

    //根据不同的账号类型，确定不同利率进行利息计算
    public double interestEarned() {
        double amount = sumTransactions();
        switch (accountType) {
            case CHECKING:
                amount *= (1 + 0.001);
                break;
            case SAVINGS:
                double tmp = (amount % 1001) * 0.001;
                if(amount > 1000) {
                    tmp += (amount - 1000) * 0.002;
                }
                amount += tmp;
                break;
            case MAXI_SAVINGS:
                double tmpMax = (amount % 1001) * 0.02;
                if(amount > 1000) {
                    tmpMax += ((amount - 1000) % 1001) * 0.05;
                }
                if(amount > 2000) {
                    tmpMax += (amount - 2000) * 0.1;
                }
                amount += tmpMax;
                break;
        }
        return amount;
    }

    //返回账号余额
    public double sumTransactions() {
        double amount = 0.0;
        for(Transaction each : this.transactions) {
            amount += each.getAmount();
        }
        return amount;
    }

    public int getAccountType() {
        return accountType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        switch (this.accountType) {
            case CHECKING:
                sb.append("Checking Account\n");
                break;
            case SAVINGS:
                sb.append("Savings Account\n");
                break;
            case MAXI_SAVINGS:
                sb.append("Maxi Savings Account\n");
                break;
        }
        for(Transaction each : transactions) {
            sb.append("  ");
            sb.append(each);
            sb.append("\n");
        }
        sb.append("Total $");
        sb.append(sumTransactions());
        sb.append("\n");
        return sb.toString();
    }
}
