

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Customer {
    private String name;
    private List<Account> accounts;

    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<Account>();
    }

    public String getName() {
        return name;
    }

    public Customer openAccount(Account account) {
        accounts.add(account);
        return this;
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }
    public double totalAccounts(){
        double total=0;
        for(Account each:
            accounts){
            total+=each.sumTransactions();
        }
        return total;
    }
    //计算客户各账户的总利率
    public double totalInterestEarned() {
        double total = 0;
        for (int i = 0; i < accounts.size(); i++) {
            total += accounts.get(i).interestEarned();
        }
        return total;
    }

    //获取所有账户详情
    public String getStatement() {
        /*
        "Statement for Henry\n" +
                "\n" +
                "Checking Account\n" +
                "  deposit $100.00\n" +
                "  withdrawal $50.00\n" +
                "Total $50.00\n" +
                "\n" +
                "Savings Account\n" +
                "  deposit $4,000.00\n" +
                "  withdrawal $200.00\n" +
                "Total $3,800.00\n" +
                "\n" +
                "Maxi Savings Account\n" +
                "  deposit $500.00\n" +
                "Total $500.00\n" +
                "\n" +
                "Total In All Accounts $4,350.00", henry.getStatement());
         */
        String statement = "";
        StringBuilder sb = new StringBuilder();
        sb.append("Statement for " + name + "\n\n");
        for (Account each :
                accounts) {
            sb.append(each+"\n");
        }
        sb.append("Total In All Accounts "+toDollars(totalAccounts()));
        statement=sb.toString();
        return statement;
    }

    //格式化金额输出，无需修改,可直接引用
    private String toDollars(double d) {
        return String.format("$%,.2f", abs(d));
    }
}
