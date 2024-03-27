package BankSystem;

import java.sql.*;
import java.time.Period;

public class BankControllerClass {

    public Connection connect_To_DB(String dbUrl, String dbUser, String dbPassword) {
        Connection conn = null;
        try {
            //establishing connection with db.
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            if (conn != null) {
                System.out.println("***Connection to Bank Database Established***");
            } else {
                System.out.println("!!Failed to connect to Bank's Database!!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void createTable(Connection con) {
        try {
            PreparedStatement prepStat;
            prepStat = con.prepareStatement("create table bank (accountNumber SERIAL,customerName varchar(50) NOT NULL, branch varchar(100) NOT NULL, accountType varchar(100) NOT NULL, balance double precision NOT NULL,primary key(accountNumber))");
            prepStat.executeUpdate();
            System.out.println("Table created successfully....");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void createCustomer(Connection con,String customerName,String branch, String accountType, double balance){
        try{
            PreparedStatement prepStat;
            prepStat = con.prepareStatement("insert into bank (customerName,branch,accountType,balance) values(?, ?, ?, ?)");
            prepStat.setString(1,customerName);
            prepStat.setString(2,branch);
            prepStat.setString(3,accountType);
            prepStat.setDouble(4,balance);
            prepStat.executeUpdate();
            System.out.println("Customer information inserted into table bank successfully.");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // display method for account number
    public void displayAccountNumber(Connection con, String customerName){
        try{
            PreparedStatement prepStat;
            prepStat = con.prepareStatement("select * from bank where customerName = ?");
            prepStat.setString(1,customerName);
            ResultSet resultSet = prepStat.executeQuery();
            while (resultSet.next()){
                int accountNo = resultSet.getInt("accountNumber");
                System.out.println("Account number for " +customerName+ " : " +accountNo);

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void checkBalance(Connection con, int accountNo){
        try{
            PreparedStatement prepStat;
            prepStat = con.prepareStatement("select * from bank  where accountNumber = ?");
            prepStat.setInt(1,accountNo);
            prepStat.executeQuery();
            ResultSet resultSet = prepStat.executeQuery();
            while (resultSet.next()){
                int accountNumber = resultSet.getInt("accountNumber");
                String customerName = resultSet.getString("customerName");
                double balance = resultSet.getDouble("balance");
                System.out.println("**Customer Details with Total Balance Available Currently***");
                System.out.println("Account number : " +accountNo+ "\nCustomer Name : " +customerName+ "\nBalance : " +balance);
            }

            System.out.println("Customer information retrieved successfully.");

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void WithdrawMoney(Connection con, int accountNo, double balance){
        try{
            PreparedStatement prepStat;
            prepStat = con.prepareStatement("update bank set balance = balance - ? where accountNumber = ?");
            prepStat.setDouble(1,balance);
            prepStat.setInt(2,accountNo);
            prepStat.executeUpdate();
            System.out.println("*** Withdrawal Successful ***");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void findCustomerType(Connection con, String accountType){
        try {
            PreparedStatement prepStat;
            prepStat = con.prepareStatement("select * from bank where accountType = ? ");
            prepStat.setString(1,accountType);
            prepStat.executeQuery();
            ResultSet resultSet = prepStat.executeQuery();
            while (resultSet.next()){
                int accountNo = resultSet.getInt("accountNumber");
                String customerName = resultSet.getString("customerName");
                String branch = resultSet.getString("branch");
                double balance = resultSet.getDouble("balance");
                System.out.println("***Current Customer Details***");
                System.out.println("Account number : " +accountNo+ "\nCustomer Name : "+customerName + "\nBranch : " +branch + "\nBalance :" +balance);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void depositMoney(Connection con, int accountNo, double balance){
        try{
            PreparedStatement prepStat;
            prepStat = con.prepareStatement("update bank set balance = balance + ? where accountNumber = ?");
            prepStat.setDouble(1,balance);
            prepStat.setInt(2,accountNo);
            prepStat.executeUpdate();
            System.out.println("*** Amount deposited Successfully ***");
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void changeBranch(Connection con, String customerName, String branch){
        try{
            PreparedStatement prepStat;
            prepStat = con.prepareStatement("update bank set branch = ? where customerName = ?");
            prepStat.setString(1,branch);
            prepStat.setString(2,customerName);
            prepStat.executeUpdate();
            System.out.println("*** Changes in the Branch column is made successfully ***");
        }catch (SQLException e){
            e.printStackTrace();

        }
    }

    public void printAllCustomer(Connection con){
        try{
            PreparedStatement prepStat;
            prepStat = con.prepareStatement("select * from bank");
            ResultSet resultSet = prepStat.executeQuery();
            while (resultSet.next()){
                int accountNo = resultSet.getInt("accountNumber");
                String customerName = resultSet.getString("customerName");
                String branch = resultSet.getString("branch");
                String accountType = resultSet.getString("accountType");
                double balance = resultSet.getDouble("balance");
                System.out.println("**************************\n"+"Account number : " +accountNo+ "\nCustomer Name : "+customerName + "\nBranch : " +branch + "" +
                        "\nAccount Type : " +accountType+ "\nBalance :" +balance+"\n"+"**************************");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteCustomer(Connection con, int accountNo){
        try{
            PreparedStatement prepStat;
            prepStat = con.prepareStatement("delete from bank where accountNumber = ?");
            prepStat.setInt(1,accountNo);
            int rowAffected = prepStat.executeUpdate();
            if(rowAffected > 0){
                System.out.println("**Customer with Account Number " + accountNo + " Deleted Successfully**");
            }else {
                System.out.println("**Customer with Account Number " +accountNo+ " not found**");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public boolean isAccountExists(Connection con, int accountNo){
        try{
            PreparedStatement prepStat;
            prepStat = con.prepareStatement("select count(*) from bank where accountNumber = ?");
            prepStat.setInt(1,accountNo);
            ResultSet resultSet = prepStat.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            return  count > 0;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }


}


