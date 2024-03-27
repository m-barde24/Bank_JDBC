package BankSystem;

public class BankUserClass {
  private int accountNo;
  private String customerName;
  private String branch;
  private String accountType;
  private double balance;

    public BankUserClass(int accountNo, String customerName, String branch, String accountType, double balance) {
        this.accountNo = accountNo;
        this.customerName = customerName;
        this.branch = branch;
        this.accountType = accountType;
        this.balance = balance;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "BankUserClass{" +
                "accountNo=" + accountNo +
                ", customerName='" + customerName + '\'' +
                ", branch='" + branch + '\'' +
                ", accountType='" + accountType + '\'' +
                ", balance=" + balance +
                '}';
    }
}
