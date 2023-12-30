/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.FinancialTransaction;
import utils.DBContext;

/**
 *
 * @author vothimaihoa
 */
public class FinancialTransactionDao extends DBContext {

    public List<FinancialTransaction> getAll() {
        List<FinancialTransaction> list = new ArrayList<>();
        String sql = "SELECT * FROM Financial_Transactions";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                FinancialTransaction transaction = new FinancialTransaction();
                transaction.setId(rs.getInt("id"));
                transaction.setRegionId(rs.getInt("region_id"));
                transaction.setTransactionType(rs.getString("transaction_type"));
                transaction.setAmount(rs.getDouble("amount"));
                transaction.setDescription(rs.getString("description"));
                transaction.setTransactionDate(rs.getTimestamp("transaction_date"));
                list.add(transaction);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public List<FinancialTransaction> getByRegionId(int regionId) {
        List<FinancialTransaction> list = new ArrayList<>();
        String sql = "SELECT * FROM Financial_Transactions where region_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, regionId);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                FinancialTransaction transaction = new FinancialTransaction();
                transaction.setId(rs.getInt("id"));
                transaction.setRegionId(rs.getInt("region_id"));
                transaction.setTransactionType(rs.getString("transaction_type"));
                transaction.setAmount(rs.getDouble("amount"));
                transaction.setDescription(rs.getString("description"));
                transaction.setTransactionDate(rs.getTimestamp("transaction_date"));
                list.add(transaction);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<FinancialTransaction> getListByPage(List<FinancialTransaction> list,
            int start, int end) {
        List<FinancialTransaction> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public void insert(FinancialTransaction newTransaction) {
        String sql = "insert into Financial_Transactions(region_id, transaction_type, amount, description, transaction_date) values(?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            // set cac tham so vao query de insert
            st.setInt(1, newTransaction.getRegionId());
            st.setString(2, newTransaction.getTransactionType());
            st.setDouble(3, newTransaction.getAmount());
            st.setString(4, newTransaction.getDescription());
            st.setTimestamp(5, newTransaction.getTransactionDate());
            st.executeUpdate();
            System.out.println("Inserted!");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public static void main(String[] args) {
        FinancialTransactionDao fd = new FinancialTransactionDao();
        List<FinancialTransaction> list = fd.getAll();
        for (FinancialTransaction f : list) {
            System.out.println(f.toString());
        }
    }

}
