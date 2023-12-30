/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import utils.DBContext;

/**
 *
 * @author vothimaihoa
 */
public class AccountDao extends DBContext{
    public Account login(String username, String password, String usertype) {
        Account x = null; 
        String sql = "select * from [User] where username = ? and password = ? and user_type = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            st.setString(3, usertype);
            ResultSet rs = st.executeQuery();
            if (rs.next()) { 
                Account c = new Account();
                c.setId(rs.getInt("id"));
                c.setUsername(rs.getString("username"));
                c.setPassword(rs.getString("password"));
                c.setUsertype(rs.getString("user_type"));
                x = c; 
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }
    
    public void insert(Account account) {
        String sql = "insert into [User](username, password, user_type) values(?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            // Set các tham số vào query để insert
            st.setString(1,account.getUsername());
            st.setString(2,account.getPassword());
            st.setString(3, account.getUsertype());
            st.executeUpdate();
            System.out.println("Inserted!");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void changePassword(String newPass, int id) {
        String sql = "update [User] set password = ? where id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, newPass);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public static void main(String[] args) {
        AccountDao acd = new AccountDao();
        
//        Account ac = acd.login("augustus", "123", "emperor");
        acd.insert(new Account("lily", "000", "consul"));
        Account ac2 = acd.login("lily", "000", "consul");
        System.out.println(ac2.toString());
    }
}
