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
import model.Account;
import model.Consul;
import utils.DBContext;

/**
 *
 * @author vothimaihoa
 */
public class ConsulDao extends DBContext {

    public List<Consul> getAll() {
        List<Consul> list = new ArrayList<>();
        String sql = "select c.*, u.username from Consul c join [User] u  on c.id = u.id where c.id <> 9";
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
                c.setUsername(rs.getString("username"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Consul getById(int id) {
        Consul result = null;
        String sql = "select c.*, u.username from Consul c join [User] u  on c.id = u.id where c.id = ?";
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
                c.setUsername(rs.getString("username"));
                result = c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

    public void insert(Consul consul) {
        String sql = "insert into Consul(id, name, age, address, term_count, salary, noble_status, image) values(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            // Set các tham số vào query để insert
            st.setInt(1, consul.getId());
            st.setString(2, consul.getName());
            st.setInt(3, consul.getAge());
            st.setString(4, consul.getAddress());
            st.setInt(5, consul.getTermCount());
            st.setDouble(6, consul.getSalary());
            st.setBoolean(7, consul.isNobleStatus());
            st.setString(8, consul.getImage());
            st.executeUpdate();
            System.out.println("Inserted!");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
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
//        ConsulDao cd = new ConsulDao();
//        List<Consul> c = cd.getAll();
//        for (Consul x : c) {
//            System.out.println(x.toString());
//        }
//        Consul c2 = cd.getById(3);
//        System.out.println(c2.toString());

//        cd.insert(new Consul(11, "Lily", 30, "123, Rose Street, France", 1, 30000, true, ""));

    }
}
