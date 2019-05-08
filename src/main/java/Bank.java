

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Customer> customers;

    public Bank() {
        customers = new ArrayList<Customer>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    //返回固定格式的客户列表,格式参考测试用例
    public String customerSummary() {
        String summary = "Customer Summary\n";
        int NumOfCustomers = this.customers.size();
        if(NumOfCustomers == 1){
            Customer temp = customers.get(0);
            summary = summary + " - " + temp.getName() + " " + "(" + temp.getNumberOfAccounts() + " accounts)";
        }
        if(NumOfCustomers >= 2) {
            for (int i = 0; i < NumOfCustomers - 1; i++) {
                Customer temp = customers.get(i);
                summary = summary + " - " + temp.getName() + " " + "(" + temp.getNumberOfAccounts() + " accounts)";
                summary = summary + "\n";
            }
            Customer FinalCustomer = customers.get(customers.size() - 1);
            summary = summary + " - " + FinalCustomer.getName() + " " + "(" + FinalCustomer.getNumberOfAccounts() + " accounts)";
        }
        return summary;
    }

    //返回支付所有客户的利息总和
    public double totalInterestPaid() {
        double total = 0;
        if(this.customers.size() != 0){
            for(int i = 0; i < this.customers.size(); i++){
                Customer temp = customers.get(i);
                double InterestOfCustomer = temp.totalInterestEarned();
                total += InterestOfCustomer;
            }
        }
        return total;
    }

}
