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
        
    public static void main(String[] args) {
        AccountDao acd = new AccountDao();
        
        Account ac = acd.login("augustus", "123", "emperor");
        System.out.println(ac.toString());
    }
}
