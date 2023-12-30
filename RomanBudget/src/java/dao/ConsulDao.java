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
import model.Consul;
import utils.DBContext;

/**
 *
 * @author vothimaihoa
 */
public class ConsulDao extends DBContext{
    public List<Consul> getAll() {
        List<Consul> list = new ArrayList<>();
        String sql = "select * from Consul where id <> 9";
        try {
           
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Consul c = new Consul();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setAge(rs.getInt("age"));
                c.setAddress(rs.getString("address"));
                c.setTermCount(rs.getInt("term_count"));
                c.setSalary(rs.getDouble("salary"));
                c.setNobleStatus(rs.getBoolean("noble_status"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public Consul getById(int id) {
        Consul result = null;
        String sql = "select * from Consul where id = ?";
        try {
           
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Consul c = new Consul();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setAge(rs.getInt("age"));
                c.setAddress(rs.getString("address"));
                c.setTermCount(rs.getInt("term_count"));
                c.setSalary(rs.getDouble("salary"));
                c.setNobleStatus(rs.getBoolean("noble_status"));
                c.setImage(rs.getString("image"));
                result = c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }
    
    // phan trang
    // thong tin page va numperpage duoc de o trong doGet
    public List<Consul> getListByPage(List<Consul> list,
            int start, int end) {
        List<Consul> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public static void main(String[] args) {
        ConsulDao cd = new ConsulDao();
        List<Consul> c = cd.getAll();
        for (Consul x : c) {
            System.out.println(x.toString());
        }
        Consul c2 = cd.getById(3);
        System.out.println(c2.toString());
    }
}
